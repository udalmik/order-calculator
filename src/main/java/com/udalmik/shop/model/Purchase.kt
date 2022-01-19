package com.udalmik.shop.model

import java.math.BigDecimal

class Purchase(val item: Item, val quantity: Long) {
    val totalPrice: BigDecimal
        get() = item.price.multiply(BigDecimal.valueOf(quantity))
}