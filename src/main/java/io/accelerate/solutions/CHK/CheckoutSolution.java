package io.accelerate.solutions.CHK;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        if (skus != null && skus.chars()
                .allMatch(ch -> ch == 'A' || ch == 'B' || ch == 'C' || ch == 'D' || ch == 'E')) {
            var acount = (int) skus.chars().filter(a -> a == 'A').count();
            var bcount = (int) skus.chars().filter(b -> b == 'B').count();
            var ccount = (int) skus.chars().filter(c -> c == 'C').count();
            var dcount = (int) skus.chars().filter(d -> d == 'D').count();
            var ecount = (int) skus.chars().filter(d -> d == 'E').count();

            var cprice = ccount * 20;
            var dprice = dcount * 15;
            var eprice = ecount * 40;
            var aprice = ((acount / 3) * 130) + ((acount % 3) * 50);
            if (ecount >= 2) {

                var bprice = ((bcount / 2) * 45) + ((bcount % 2) * 30);
            }
            return cprice + dprice + aprice + bprice + eprice;
        }
        return -1;
    }
}
