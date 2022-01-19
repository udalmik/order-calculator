package com.udalmik.shop

import com.udalmik.shop.model.Item
import com.udalmik.shop.model.Purchase
import com.udalmik.shop.model.PurchaseOrder
import com.udalmik.shop.promo.StaticPromosService
import com.udalmik.shop.promo.ThirdFreePromo
import com.udalmik.shop.promo.ThirdHalfPricePromo
import java.math.BigDecimal

fun main() {

    var itemId: Long = 0

    // Items
    val rockyRoad = Item(++itemId, "Rocky Road", BigDecimal.valueOf(8))
    val cookiesAndCream = Item(++itemId, "Cookies & Cream", BigDecimal.valueOf(10))
    val netflixXChill = Item(++itemId, "Netflix x Chill", BigDecimal.valueOf(12))

    // Promos
    val staticPromos = mapOf(rockyRoad.id to ThirdFreePromo(), cookiesAndCream.id to ThirdHalfPricePromo())

    val promosService = StaticPromosService(staticPromos)

    // Order Calculator
    val orderCalculator = OrderCalculator(promosService)
    val purchases = listOf(
            Purchase(rockyRoad, 1),
            Purchase(cookiesAndCream, 3),
            Purchase(netflixXChill, 2)
    )
    val purchaseOrder = PurchaseOrder(purchases)
    val totalOrder = orderCalculator.calculateTotalOrder(purchaseOrder)
    println(totalOrder)
}