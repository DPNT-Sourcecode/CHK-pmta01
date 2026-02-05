package io.accelerate.solutions.CHK;

public class CheckoutSolution {
    public Integer checkout(String skus) {

       // if(!skus.chars().allMatch()) {return -1;}

        var acount = (int) skus.chars().filter(a -> a == 'A').count();
        var bcount = (int) skus.chars().filter(b -> b == 'B').count();
        var ccount = (int) skus.chars().filter(c -> c == 'C').count();
        var dcount = (int) skus.chars().filter(d -> d == 'D').count();

        var cprice = ccount * 20;
        var dprice = dcount * 15;
        var aprice = ((acount / 3) * 130) + ((acount % 3) * 50);
        var bprice = ((bcount / 2) * 45) + ((bcount % 2) * 30);

        return cprice + dprice + aprice + bprice;

    }
}

