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
        final var purchase = new Purchase(createItemOfPrice(BigDecimal.ONE), 2);
        assertEquals(
                BigDecimal.ZERO.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE),
                itemPromo.getDiscount(purchase));
    }

    @Test
    public void testDiscountWhenOneWithDiscount() {
        final var purchase = new Purchase(createItemOfPrice(BigDecimal.valueOf(2)), 3);
        assertEquals(
                BigDecimal.ONE.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE),
                itemPromo.getDiscount(purchase));
    }

    @Test
    public void testMultipleDiscounts() {
        final var purchase = new Purchase(createItemOfPrice(BigDecimal.ONE), 7);
        assertEquals(
                BigDecimal.ONE.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE),
                itemPromo.getDiscount(purchase));
    }
}