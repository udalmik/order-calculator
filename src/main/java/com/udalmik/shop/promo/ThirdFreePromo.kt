package com.udalmik.shop.promo;

import com.udalmik.shop.model.Purchase;

import java.math.BigDecimal;

public class ThirdFreePromo implements ItemPromo {
    @Override
    public BigDecimal getDiscount(Purchase purchase) {
        final var price = purchase.getItem().getPrice();
        final var quantity = purchase.getQuantity();
        final var freeItemsCount = quantity / 3;
        return price.multiply(BigDecimal.valueOf(freeItemsCount));
    }
}
