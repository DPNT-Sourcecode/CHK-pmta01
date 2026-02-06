package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            Map<Character, Integer> counts = getItemsCount(skus);
            Map<Character, Integer> payable = new HashMap<>(counts);

            applyCrossItemFreebie('E', 2, 'B', 1, payable);
            applyCrossItemFreebie('F', 2, 'F', 1, payable);
            applyCrossItemFreebie('M', 3, 'N', 1, payable);
            applyCrossItemFreebie('R', 3, 'Q', 1, payable);
            applyCrossItemFreebie('U', 3, 'U', 1, payable);

            var totalPrice = 0;

            for (var entry : payable.entrySet()) {
                var sku = entry.getKey();
                var quantity = entry.getValue();
                totalPrice += totalPriceForSku(sku, quantity);
            }

            return totalPrice;
        }
        return -1;
    }

    private boolean skuIsValid(String skus) {
        return skus != null && skus.matches("^[A-Z]*$");
    }

    private Map<Character, Integer> getItemsCount(String skus) {
        Map<Character, Integer> itemsCountMap = new HashMap<>();
        skus.chars().forEach(ch -> itemsCountMap.merge((char) ch, 1, Integer::sum));
        return itemsCountMap;
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

    private void applyCrossItemFreebie(char triggerSku, int triggerCount,
                                       char affectedSku, int affectedCount, Map<Character, Integer> payable) {

        var triggerQuantity = payable.getOrDefault(triggerSku, 0);
        var affectedSkuQuantity = payable.getOrDefault(affectedSku, 0);

        var triggerRepetitionTime = triggerQuantity / triggerCount;
        var freebies = triggerRepetitionTime * affectedCount;

        payable.put(affectedSku, Math.max(0, affectedSkuQuantity - freebies));

        if (triggerSku == affectedSku) { //get 2f, third f will be free
            var newCount = triggerCount + affectedCount;
            var sameItemFreebie = triggerQuantity / newCount;
            payable.put(affectedSku, triggerQuantity - sameItemFreebie);
        }
    }

    private int totalPriceForSku(char sku, int quantity) {

        Set<Character> TWO_TYPE_OFFER_ITEMS = Set.of('A', 'H', 'V');
        Set<Character> ONE_TYPE_OFFER_ITEMS = Set.of('B', 'K', 'P', 'Q');

        if (TWO_TYPE_OFFER_ITEMS.contains(sku)) {
            return twoTypeOfferPricing(sku, quantity);
        }
        if (ONE_TYPE_OFFER_ITEMS.contains(sku)) {
            return oneTypeOfferPricing(sku, quantity);
        }
        return quantity * PRICE_BY_SKU.get(sku);
    }

    private int oneTypeOfferPricing(char sku, int quantity) {
        if (itemsWithBatchDiscounts.contains(sku)) {
            int totalPrice = 0;
            switch (sku) {
                case 'B' -> totalPrice = oneTypeBatchDiscountPrice(quantity, 45, 2, PRICE_BY_SKU.get(sku));
                case 'K' -> totalPrice = oneTypeBatchDiscountPrice(quantity, 150, 2, PRICE_BY_SKU.get(sku));
                case 'P' -> totalPrice = oneTypeBatchDiscountPrice(quantity, 200, 5, PRICE_BY_SKU.get(sku));
                case 'Q' -> totalPrice = oneTypeBatchDiscountPrice(quantity, 80, 3, PRICE_BY_SKU.get(sku));
            }
            return totalPrice;
        }
        return 0;
    }

    private int twoTypeOfferPricing(char sku, int quantity) {
        if (itemsWithBatchDiscounts.contains(sku)) {
            var totalPrice = 0;
            switch (sku) {
                case 'A' -> totalPrice =
                        twoTypeBatchDiscountPrice(quantity, 3, 130, 5, 200, PRICE_BY_SKU.get(sku));
                case 'H' -> totalPrice =
                        twoTypeBatchDiscountPrice(quantity, 5, 45, 10, 80, PRICE_BY_SKU.get(sku));
                case 'V' -> totalPrice =
                        twoTypeBatchDiscountPrice(quantity, 2, 90, 3, 130, PRICE_BY_SKU.get(sku));
            }
            return totalPrice;
        }
        return 0;
    }

}
