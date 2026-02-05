package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    private final CheckoutSolution checkoutSolution = new CheckoutSolution();

    @Test
    void calculateTotalPriceOfProducts() {

        final String sku1 = "ABCD";
        final String sku2 = "AAACD";
        final String sku3 = "ABBC";
        final String sku4 = "CCD";
        final String sku5 = "DDAAB";
        final String sku6 = "";
        final String sku7 = "EE";
        final String sku8 = "EEBB";
        final String sku9 = "AEB";
        final String sku10 = "CDEEB";
        final String sku11 = "CBABBDEEEE";
        final String sku12 = "AAAAA";
        final String sku13 = "AAAAAAAA";
        final String sku14 = "AAAAAA";
        final String sku15 = "AAAAAAAAAA";

        assertEquals(115, (int) checkoutSolution.checkout(sku1));
        assertEquals(165, (int) checkoutSolution.checkout(sku2));
        assertEquals(115, (int) checkoutSolution.checkout(sku3));
        assertEquals(55, (int) checkoutSolution.checkout(sku4));
        assertEquals(160, (int) checkoutSolution.checkout(sku5));
        assertEquals(0, (int) checkoutSolution.checkout(sku6));
        assertEquals(80, (int) checkoutSolution.checkout(sku7));
        assertEquals(110, (int) checkoutSolution.checkout(sku8));
        assertEquals(120, (int) checkoutSolution.checkout(sku9));
        assertEquals(115, (int) checkoutSolution.checkout(sku10));
        assertEquals(275, (int) checkoutSolution.checkout(sku11));
        assertEquals(200, (int) checkoutSolution.checkout(sku12));
        assertEquals(330, (int) checkoutSolution.checkout(sku13));
        assertEquals(250, (int) checkoutSolution.checkout(sku14));
        assertEquals(400, (int) checkoutSolution.checkout(sku15));

    }

    @Test
    void skusOnlyIncludeAcceptedProductIdentifiers() {
        final String nonValidSku1 = "ADECBA";
        final String nonValidSku3 = null;
        final String nonValidSku4 = "RT";

        assertEquals(-1, checkoutSolution.checkout(nonValidSku1));
        assertEquals(-1, checkoutSolution.checkout(nonValidSku3));
        assertEquals(-1, checkoutSolution.checkout(nonValidSku4));
    }
}

