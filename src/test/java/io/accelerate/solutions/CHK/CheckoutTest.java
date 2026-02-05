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

        final String option1 = "ABCD";
        final String option2 = "AAACD";
        final String option3 = "ABBC";
        final String option4 = "CCD";
        final String option5 = "DDAAB";

        assertEquals(115, (int) checkoutSolution.checkout(option1));
        assertEquals(165, (int) checkoutSolution.checkout(option2));
        assertEquals(115, (int) checkoutSolution.checkout(option3));
        assertEquals(55, (int) checkoutSolution.checkout(option4));
        assertEquals(160, (int) checkoutSolution.checkout(option5));
    }
}

