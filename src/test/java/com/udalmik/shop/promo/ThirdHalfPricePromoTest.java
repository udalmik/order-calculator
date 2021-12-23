package com.udalmik.shop.promo;

import com.udalmik.shop.model.Purchase;
import org.junit.Test;

import java.math.BigDecimal;

import static com.udalmik.shop.CalculatorConstants.DEFAULT_ROUNDING_MODE;
import static com.udalmik.shop.CalculatorConstants.DEFAULT_SCALE;
import static com.udalmik.shop.TestData.createItemOfPrice;
import static org.junit.Assert.assertEquals;

public class ThirdHalfPricePromoTest {

    private final ItemPromo itemPromo = new ThirdHalfPricePromo();

    @Test
    public void testNoDiscountWhenTwoItems() {
        final var purchase = Purchase.builder()
                .quantity(2)
                .item(createItemOfPrice(BigDecimal.ONE))
                .build();

        assertEquals(
                BigDecimal.ZERO.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE),
                itemPromo.getDiscount(purchase));
    }

    @Test
    public void testDiscountWhenOneWithDiscount() {
        final var purchase = Purchase.builder()
                .quantity(3)
                .item(createItemOfPrice(BigDecimal.valueOf(2)))
                .build();

        assertEquals(
                BigDecimal.ONE.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE),
                itemPromo.getDiscount(purchase));
    }

    @Test
    public void testMultipleDiscounts() {
        final var purchase = Purchase.builder()
                .quantity(7)
                .item(createItemOfPrice(BigDecimal.ONE))
                .build();

        assertEquals(
                BigDecimal.ONE.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE),
                itemPromo.getDiscount(purchase));
    }
}