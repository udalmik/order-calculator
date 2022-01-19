package com.udalmik.shop

import com.udalmik.shop.model.Purchase
import com.udalmik.shop.model.PurchaseOrder
import com.udalmik.shop.promo.PromosService
import java.math.BigDecimal

class OrderCalculator(private val promosService: PromosService) {
    fun calculateTotalOrder(purchaseOrder: PurchaseOrder): TotalOrder {
        val totalAmount = purchaseOrder.purchases.stream()
                .map(Purchase::totalPrice)
                .reduce(BigDecimal.ZERO) { obj: BigDecimal, augend: BigDecimal? -> obj.add(augend) }
        val totalDiscount = purchaseOrder.purchases.stream()
                .map { purchase: Purchase -> calculateDiscount(purchase) }
                .reduce(BigDecimal.ZERO) { obj: BigDecimal, augend: BigDecimal? -> obj.add(augend) }
        return TotalOrder(totalAmount, totalDiscount)
    }

    private fun calculateDiscount(purchase: Purchase): BigDecimal {
        return promosService
                .getItemPromo(purchase.item.id)
                ?.getDiscount(purchase) ?: BigDecimal.ZERO
    }

    data class TotalOrder(val totalAmount: BigDecimal, val totalDiscount: BigDecimal) {
        val totalPay: BigDecimal
            get() = this.totalAmount.subtract(this.totalDiscount)
    }

}