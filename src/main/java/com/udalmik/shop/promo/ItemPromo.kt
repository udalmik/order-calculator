package com.udalmik.shop.promo

import com.udalmik.shop.model.Purchase
import java.math.BigDecimal

@FunctionalInterface
interface ItemPromo {
    fun getDiscount(purchase: Purchase): BigDecimal?
}