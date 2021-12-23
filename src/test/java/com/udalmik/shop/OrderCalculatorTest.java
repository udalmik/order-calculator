package com.udalmik.shop;

import com.udalmik.shop.model.Purchase;
import com.udalmik.shop.model.PurchaseOrder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.udalmik.shop.TestData.createItemsOfPrices;
import static com.udalmik.shop.TestData.noPromosCalculator;
import static org.junit.Assert.assertEquals;

public class OrderCalculatorTest {

    @Test
    public void testOrderWithoutPromo() {
        var orderCalculator = noPromosCalculator();
        var items = createItemsOfPrices(List.of(BigDecimal.ONE, BigDecimal.ONE));

        var purchaseOrder = PurchaseOrder.builder().purchases(
                List.of(
                        Purchase.builder().item(items.get(0)).quantity(2).build(),
                        Purchase.builder().item(items.get(1)).quantity(2).build())
        ).build();

        var totalOrder = orderCalculator.calculateTotalOrder(purchaseOrder);

        assertEquals(BigDecimal.valueOf(4), totalOrder.getTotalAmount());
        assertEquals(BigDecimal.ZERO, totalOrder.getTotalDiscount());
        assertEquals(totalOrder.getTotalAmount(), totalOrder.getTotalPay());
    }

}