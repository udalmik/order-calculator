package com.udalmik.shop.promo

import com.udalmik.shop.model.Purchase
import java.math.BigDecimal

class ThirdFreePromo : ItemPromo {
    override fun getDiscount(purchase: Purchase): BigDecimal {
        val price = purchase.item.price
        val quantity = purchase.quantity
        val freeItemsCount = quantity / 3
        return price.multiply(BigDecimal.valueOf(freeItemsCount))
    }
}