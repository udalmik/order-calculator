package com.udalmik.shop.promo

import com.udalmik.shop.CalculatorConstants.DEFAULT_ROUNDING_MODE
import com.udalmik.shop.CalculatorConstants.DEFAULT_SCALE
import com.udalmik.shop.model.Purchase
import java.math.BigDecimal
import java.math.BigDecimal.valueOf

class ThirdHalfPricePromo : ItemPromo {
    override fun getDiscount(purchase: Purchase): BigDecimal {
        val price = purchase.item.price
        val quantity = purchase.quantity
        val halfPriceCount = quantity / 3
        return price.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE)
                .divide(valueOf(2), DEFAULT_ROUNDING_MODE)
                .multiply(valueOf(halfPriceCount))
    }
}