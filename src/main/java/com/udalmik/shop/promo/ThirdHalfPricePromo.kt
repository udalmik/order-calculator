package com.udalmik.shop.promo

import com.udalmik.shop.CalculatorConstants
import com.udalmik.shop.model.Purchase
import java.math.BigDecimal

class ThirdHalfPricePromo : ItemPromo {
    override fun getDiscount(purchase: Purchase): BigDecimal? {
        val price = purchase.item.price
        val quantity = purchase.quantity
        val halfPriceCount = quantity / 3
        return price.setScale(CalculatorConstants.DEFAULT_SCALE, CalculatorConstants.DEFAULT_ROUNDING_MODE)
                .divide(BigDecimal.valueOf(2), CalculatorConstants.DEFAULT_ROUNDING_MODE)
                .multiply(BigDecimal.valueOf(halfPriceCount))
    }
}