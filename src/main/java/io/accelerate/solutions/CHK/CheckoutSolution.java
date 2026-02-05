package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {


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
            var cprice = itemsWithoutOfferTotalPrice(itemsCount(skus), 'C');
            var dprice = itemsWithoutOfferTotalPrice(itemsCount(skus), 'D');
            var eprice = itemsWithoutOfferTotalPrice(itemsCount(skus), 'E');

            var bonusAprice = 0;
            while (acount.get('A') >= 5) {
                bonusAprice += (acount.get('A') / 5) * 200;
                if (acount.get('A') % 5 == 0) {
                    acount.put('A', 0);
                    break;
                }
                acount -= 5;
            }
            var aprice = ((acount / 3) * 130) + ((acount % 3) * 50) + bonusAprice;

            while (ecount >= 2 && bcount > 0) {
                bcount -= 1;
                ecount -= 2;
            }
            var bprice = ((bcount / 2) * 45) + ((bcount % 2) * 30);

            var bonusFprice = 0;
            while (fcount >= 3) {
                bonusFprice += 2 * 10;
                fcount -= 3;
            }
            var fprice = bonusFprice + (fcount * 10);

            return cprice + dprice + aprice + bprice + eprice + fprice;
        }
        return -1;
    }

    private boolean skuIsValid(String skus) {
        return skus != null && skus.matches("^[A-Z]*$"); //todo refactor to validate against PRICE_BY_SKU map
    }

    private Map<Character, Integer> itemsCount(String skus) {
        Map<Character, Integer> itemsCountMap = new HashMap<>();
        skus.chars().forEach(ch -> itemsCountMap.merge((char) ch, 1, Integer::sum));
        return itemsCountMap;
    }

    private int itemsWithoutOfferTotalPrice(Map<Character, Integer> itemsCount, char itemSku) {
        return itemsCount.get(itemSku) * PRICE_BY_SKU.get(itemSku);
    }

    private int sameItemDiscountsOffer() {
    }

    private int crossItemDiscountOffers() {
    }

}

