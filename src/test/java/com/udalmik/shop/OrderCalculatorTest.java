package com.udalmik.shop;

import com.udalmik.shop.model.Purchase;
import com.udalmik.shop.model.PurchaseOrder;
import com.udalmik.shop.promo.ItemPromo;
import com.udalmik.shop.promo.PromosService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.udalmik.shop.TestData.createItemsOfPrices;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderCalculatorTest {

    @Mock
    private PromosService promosService;

    @InjectMocks
    private OrderCalculator orderCalculator;

    @Test
    public void testOrderWithoutPromo() {
        var items = createItemsOfPrices(List.of(BigDecimal.ONE, BigDecimal.ONE));
        var purchaseOrder = new PurchaseOrder(List.of(
                new Purchase(items.get(0), 2),
                new Purchase(items.get(1), 2))
        );

        when(promosService.getItemPromo(anyLong())).thenReturn(null);

        var totalOrder = orderCalculator.calculateTotalOrder(purchaseOrder);

        assertEquals(BigDecimal.valueOf(4), totalOrder.getTotalAmount());
        assertEquals(BigDecimal.ZERO, totalOrder.getTotalDiscount());
        assertEquals(totalOrder.getTotalAmount(), totalOrder.getTotalPay());
    }

    @Test
    public void testOrderWithPromoApplied() {
        ItemPromo halfPricePromo = (order) ->
                order.getTotalPrice().divide(BigDecimal.valueOf(2), RoundingMode.HALF_EVEN);
        var item = createItemsOfPrices(List.of(BigDecimal.ONE)).get(0);
        var quantity = 10;
        var purchaseOrder = new PurchaseOrder(List.of(new Purchase(item, quantity)));
        when(promosService.getItemPromo(eq(item.getId()))).thenReturn(halfPricePromo);

        var totalOrder = orderCalculator.calculateTotalOrder(purchaseOrder);

        assertEquals(BigDecimal.valueOf(10), totalOrder.getTotalAmount());
        assertEquals(BigDecimal.valueOf(5), totalOrder.getTotalDiscount());
        assertEquals(BigDecimal.valueOf(5), totalOrder.getTotalPay());
    }

}