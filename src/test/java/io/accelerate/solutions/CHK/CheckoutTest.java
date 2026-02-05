package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest {

    private final CheckoutSolution checkoutSolution = new CheckoutSolution();

    @Test
    void calculateTotalPriceOfProducts() {

        final String sku1 = "ABCD";
        final String sku2 = "AAACD";
        final String sku3 = "ABBC";
        final String sku4 = "CCD";
        final String sku5 = "DDAAB";

        assertEquals(115, (int) checkoutSolution.checkout(sku1));
        assertEquals(165, (int) checkoutSolution.checkout(sku2));
        assertEquals(115, (int) checkoutSolution.checkout(sku3));
        assertEquals(55, (int) checkoutSolution.checkout(sku4));
        assertEquals(160, (int) checkoutSolution.checkout(sku5));
    }

    @Test
    void skusOnlyIncludeAcceptedProductIdentifiers() {
        final String nonValidSku1 = "ADFCBA";
        final String nonValidSku2 = "";
        final String nonValidSku3 = null;
        final String nonValidSku4 = "RT";

        assertEquals(-1, checkoutSolution.checkout(nonValidSku1));
        assertEquals(-1, checkoutSolution.checkout(nonValidSku2));
        assertEquals(-1, checkoutSolution.checkout(nonValidSku3));
        assertEquals(-1, checkoutSolution.checkout(nonValidSku4));
    }
}
