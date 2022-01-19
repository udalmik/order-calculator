package com.udalmik.shop.promo;

import com.udalmik.shop.model.Purchase;
import org.junit.Test;

import java.math.BigDecimal;

import static com.udalmik.shop.TestData.createItemOfPrice;
import static org.junit.Assert.assertEquals;

public class ThirdFreePromoTest {

    private final ItemPromo itemPromo = new ThirdFreePromo();

    @Test
    public void testNoDiscountWhenTwoItems() {
        final var purchase = new Purchase(createItemOfPrice(BigDecimal.ONE), 2);
        assertEquals(BigDecimal.ZERO, itemPromo.getDiscount(purchase));
    }

    @Test
    public void testDiscountWhenOneFree() {
        final var purchase = new Purchase(createItemOfPrice(BigDecimal.ONE), 3);
        assertEquals(BigDecimal.ONE, itemPromo.getDiscount(purchase));
    }

    @Test
    public void testMultipleDiscounts() {
        final var purchase = new Purchase(createItemOfPrice(BigDecimal.ONE), 7);
        assertEquals(BigDecimal.valueOf(2), itemPromo.getDiscount(purchase));
    }
}