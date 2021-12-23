package com.udalmik.shop.promo;

import com.udalmik.shop.model.Purchase;

import java.math.BigDecimal;

import static com.udalmik.shop.CalculatorConstants.DEFAULT_ROUNDING_MODE;
import static com.udalmik.shop.CalculatorConstants.DEFAULT_SCALE;

public class ThirdHalfPricePromo implements ItemPromo {
    @Override
    public BigDecimal getDiscount(Purchase purchase) {
        final var price = purchase.getItem().getPrice();
        final var quantity = purchase.getQuantity();
        final var halfPriceCount = quantity / 3;
        return price.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE)
                .divide(BigDecimal.valueOf(2), DEFAULT_ROUNDING_MODE)
                .multiply(BigDecimal.valueOf(halfPriceCount));
    }
}
