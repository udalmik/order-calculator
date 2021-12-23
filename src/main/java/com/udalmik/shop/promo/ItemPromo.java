package com.udalmik.shop.promo;

import com.udalmik.shop.model.Purchase;

import java.math.BigDecimal;

public interface ItemPromo {

    BigDecimal getDiscount(Purchase purchase);
}
