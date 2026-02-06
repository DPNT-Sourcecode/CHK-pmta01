package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {
    private final List<Character> itemsWithBatchDiscounts = List.of('A', 'B', 'F', 'H', 'K', 'P', 'Q', 'U', 'V');
    private static final Map<Character, Integer> PRICE_BY_SKU = new HashMap<>();

    static {
        PRICE_BY_SKU.put('A', 50);
        PRICE_BY_SKU.put('B', 30);
        PRICE_BY_SKU.put('C', 20);
        PRICE_BY_SKU.put('D', 15);
        PRICE_BY_SKU.put('E', 40);
        PRICE_BY_SKU.put('F', 10);
        PRICE_BY_SKU.put('G', 20);
        PRICE_BY_SKU.put('H', 10);
        PRICE_BY_SKU.put('I', 35);
        PRICE_BY_SKU.put('J', 60);
        PRICE_BY_SKU.put('K', 80);
        PRICE_BY_SKU.put('L', 90);
        PRICE_BY_SKU.put('M', 15);
        PRICE_BY_SKU.put('N', 40);
        PRICE_BY_SKU.put('O', 10);
        PRICE_BY_SKU.put('P', 50);
        PRICE_BY_SKU.put('Q', 30);
        PRICE_BY_SKU.put('R', 50);
        PRICE_BY_SKU.put('S', 30);
        PRICE_BY_SKU.put('T', 20);
        PRICE_BY_SKU.put('U', 40);
        PRICE_BY_SKU.put('V', 50);
        PRICE_BY_SKU.put('W', 20);
        PRICE_BY_SKU.put('X', 90);
        PRICE_BY_SKU.put('Y', 10);
        PRICE_BY_SKU.put('Z', 50);
    }

    public Integer checkout(String skus) {
        if (skuIsValid(skus)) {

            var itemsWithoutOfferPrice = itemsWithoutOfferTotalPrice(getItemsCount(skus));

            var x = sameItemDiscountsOfferWithOneOfferType(skus, 'B');
            var y = sameItemDiscountsOfferWithOneOfferType(skus, 'K');
            var z = sameItemDiscountsOfferWithOneOfferType(skus, 'Q');
            var v = sameItemDiscountsOfferWithOneOfferType(skus, 'P');

            var a = crossItemDiscountOffers(skus, 'A');
            var h = crossItemDiscountOffers(skus, 'H');

//            while (ecount >= 2 && bcount > 0) {
//                bcount -= 1;
//                ecount -= 2;
//            }
//            // var bprice = ((bcount / 2) * 45) + ((bcount % 2) * 30);
//
//
//            var bonusFprice = 0;
//            while (fcount >= 3) {
//                bonusFprice += 2 * 10;
//                fcount -= 3;
//            }
//            var fprice = bonusFprice + (fcount * 10);

            return itemsWithoutOfferPrice + x + y + z + v + a + h;
        }
        return -1;
    }

    private boolean skuIsValid(String skus) {
        return skus != null && skus.matches("^[A-Z]*$"); //todo refactor to validate against PRICE_BY_SKU map
    }

    private Map<Character, Integer> getItemsCount(String skus) {
        Map<Character, Integer> itemsCountMap = new HashMap<>();
        skus.chars().forEach(ch -> itemsCountMap.merge((char) ch, 1, Integer::sum));
        return itemsCountMap;
    }

    private int itemsWithoutOfferTotalPrice(Map<Character, Integer> itemsCount) {
        var totalPrice = 0;
        var itemsToConsider = PRICE_BY_SKU.keySet().stream().
                filter(ch -> !itemsWithBatchDiscounts.contains(ch)).toList();

        for (char ch : itemsToConsider) {
            var itemCount = itemsCount.getOrDefault(ch, 0);
            var itemPrice = PRICE_BY_SKU.get(ch);
            totalPrice += itemPrice * itemCount;
        }
        return totalPrice;
    }

    private int oneTypeBatchDiscountPrice(int quantity, int discountedPrice, int discountedBatchSize, int originalPrice) {
        var totalPrice = 0;
        return totalPrice + (((quantity / discountedBatchSize) * discountedPrice) + ((quantity % 2) * originalPrice));
    }

    private int twoTypeBatchDiscountPrice(int quantity, int batchSize1, int discountedPrice1,
                                          int batchSize2, int discountedPrice2, int originalPrice) {
        var totalPrice = 0;

        var bigBatch = Math.max(batchSize1, batchSize2);
        var smallBatch = Math.min(batchSize1, batchSize2);

        var pricier = Math.max(discountedPrice1, discountedPrice2);
        var cheaper = Math.min(discountedPrice1, discountedPrice2);

        var bigBundleCount = quantity / bigBatch;
        var remainingItemsFromBigBatch = quantity % bigBatch;

        var smallBundleCount = remainingItemsFromBigBatch / smallBatch;
        var allRemainingItems = remainingItemsFromBigBatch % smallBatch;

        if (quantity >= bigBundleCount * bigBatch) {
            totalPrice += bigBundleCount * pricier;
            if (remainingItemsFromBigBatch >= smallBundleCount * smallBatch) {
                totalPrice += smallBundleCount * cheaper;
            }
        }
        totalPrice += allRemainingItems * originalPrice;
        return totalPrice;
    }

    private int freeb

    private int getSingleItemCount(String skus, char sku) {
        return getItemsCount(skus).getOrDefault(sku, 0); //todo refactor this
    }

    private int sameItemDiscountsOfferWithOneOfferType(String skus, char sku) {
        if (itemsWithBatchDiscounts.contains(sku)) {
            int totalPrice = 0;
            switch (sku) {  // todo refactor to use another logic instead of switch case
                case 'B' -> {
                    var bcount = getSingleItemCount(skus, 'B');
                    totalPrice = oneTypeBatchDiscountPrice(bcount, 45, 2, PRICE_BY_SKU.get('B'));
                }
                case 'K' -> {
                    var kcount = getSingleItemCount(skus, 'K');
                    totalPrice = oneTypeBatchDiscountPrice(kcount, 150, 2, PRICE_BY_SKU.get('K'));
                }
                case 'P' -> {
                    var pcount = getSingleItemCount(skus, 'P');
                    totalPrice = oneTypeBatchDiscountPrice(pcount, 200, 5, PRICE_BY_SKU.get('P'));
                }
                case 'Q' -> {
                    var qcount = getSingleItemCount(skus, 'Q');
                    totalPrice = oneTypeBatchDiscountPrice(qcount, 80, 3, PRICE_BY_SKU.get('Q'));
                }
            }
            return totalPrice;
        } else return 0;
    }

    private int crossItemDiscountOffers(String skus, char sku) {
        if (itemsWithBatchDiscounts.contains(sku)) {
            var totalPrice = 0;
            switch (sku) {
                case 'A' -> {
                    var acount = getSingleItemCount(skus, 'A');
                    totalPrice = twoTypeBatchDiscountPrice(acount, 3, 130, 5, 200, 50);
                }
                case 'H' -> {
                    var hcount = getSingleItemCount(skus, 'H');
                    totalPrice = twoTypeBatchDiscountPrice(hcount, 5, 45, 10, 80, 10);
                }
            }
            return totalPrice;
        } else return 0;
    }

}

