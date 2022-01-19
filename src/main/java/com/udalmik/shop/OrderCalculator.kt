package com.udalmik.shop;

import com.udalmik.shop.model.Purchase;
import com.udalmik.shop.model.PurchaseOrder;
import com.udalmik.shop.promo.PromosService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
public class OrderCalculator {

    private final PromosService promosService;

    public TotalOrder calculateTotalOrder(PurchaseOrder purchaseOrder) {
        var totalAmount = purchaseOrder.getPurchases().stream()
                .map(Purchase::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var totalDiscount = purchaseOrder.getPurchases().stream()
                .map(this::calculateDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return TotalOrder.builder()
                .totalAmount(totalAmount)
                .totalDiscount(totalDiscount)
                .build();
    }

    private BigDecimal calculateDiscount(Purchase purchase) {
        return promosService.getItemPromo(purchase.getItem().getId())
                .map(itemPromo -> itemPromo.getDiscount(purchase))
                .orElse(BigDecimal.ZERO);
    }

    @Getter
    @Builder
    @ToString
    public static class TotalOrder {
        private final BigDecimal totalAmount;
        private final BigDecimal totalDiscount;
        public BigDecimal getTotalPay() {
            return getTotalAmount().subtract(getTotalDiscount());
        }
    }
}
