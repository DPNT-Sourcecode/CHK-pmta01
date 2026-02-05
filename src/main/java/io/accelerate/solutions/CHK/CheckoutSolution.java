package io.accelerate.solutions.CHK;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        if (skusAccepted(skus)) {
            var acount = getItemCount(skus, 'A');
            var bcount = getItemCount(skus, 'B');
            var ccount = getItemCount(skus, 'C');
            var dcount = getItemCount(skus, 'D');
            var ecount = getItemCount(skus, 'E');
            var fcount = getItemCount(skus, 'F');

            var cprice = ccount * 20;
            var dprice = dcount * 15;
            var eprice = ecount * 40;

            var bonusAprice = 0;
            while (acount >= 5) {
                bonusAprice += (acount / 5) * 200;
                if (acount % 5 == 0) {
                    acount = 0;
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
            if (fcount >= 3) {
                bonusFprice += 2 * 10;
                fcount -= 3;
            }
            var fprice = bonusFprice + (fcount * 10);

            return cprice + dprice + aprice + bprice + eprice;
        }
        return -1;
    }

    private boolean skusAccepted(String skus) {
        return skus != null && skus.chars()
                .allMatch(ch -> ch == 'A' || ch == 'B' || ch == 'C' || ch == 'D' || ch == 'E' || ch == 'F');
    }

    private int getItemCount(String skus, char i) {
        return (int) skus.chars().filter(a -> a == i).count();
    }


//    private static class Item {
//        private char sku;
//        private int price;
//    }
}

