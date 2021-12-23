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
        final var purchase = Purchase.builder()
                .quantity(2)
                .item(createItemOfPrice(BigDecimal.ONE))
                .build();

        assertEquals(BigDecimal.ZERO, itemPromo.getDiscount(purchase));
    }

    @Test
    public void testDiscountWhenOneFree() {
        final var purchase = Purchase.builder()
                .quantity(3)
                .item(createItemOfPrice(BigDecimal.ONE))
                .build();

        assertEquals(BigDecimal.ONE, itemPromo.getDiscount(purchase));
    }

    @Test
    public void testMultipleDiscounts() {
        final var purchase = Purchase.builder()
                .quantity(7)
                .item(createItemOfPrice(BigDecimal.ONE))
                .build();

        assertEquals(BigDecimal.valueOf(2), itemPromo.getDiscount(purchase));
    }
}