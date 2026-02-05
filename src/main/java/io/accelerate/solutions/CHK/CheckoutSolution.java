package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        if (skuIsValid(skus)) {
            var acount = itemsCount(skus, 'A');
            var bcount = itemsCount(skus, 'B');
            var ccount = itemsCount(skus, 'C');
            var dcount = itemsCount(skus, 'D');
            var ecount = itemsCount(skus, 'E');
            var fcount = itemsCount(skus, 'F');

            var cprice = ccount.get('C') * 20;
            var dprice = dcount.get('D') * 15;
            var eprice = ecount.get('E') * 40;

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
        return skus != null && skus.matches("^[A-Z]*$");
    }

    private Map<Character, Integer> itemsCount(String skus, char i) {
        Map<Character, Integer> itemsCountMap = new HashMap<>();
        skus.chars().forEach(
                ch -> itemsCountMap.put((char) ch, (int) skus.chars().filter(a -> a == i).count()));

        return itemsCountMap;
    }

   // private Map<Character, Item> todo come back to this

    private record Item(char sku, int price) {
    }
}



