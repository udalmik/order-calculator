package com.udalmik.shop;

import com.udalmik.shop.model.Item;
import com.udalmik.shop.model.Purchase;
import com.udalmik.shop.model.PurchaseOrder;
import com.udalmik.shop.promo.StaticPromosService;
import com.udalmik.shop.promo.ThirdFreePromo;
import com.udalmik.shop.promo.ThirdHalfPricePromo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        long itemId = 0;

        // Items

        final var rockyRoad = Item.builder()
                .id(++itemId)
                .name("Rocky Road")
                .price(BigDecimal.valueOf(8))
                .build();
        final var cookiesAndCream = Item.builder()
                .id(++itemId)
                .name("Cookies & Cream")
                .price(BigDecimal.valueOf(10))
                .build();
        final var netflixXChill = Item.builder()
                .id(++itemId)
                .name("Netflix x Chill")
                .price(BigDecimal.valueOf(12))
                .build();

        // Promos

        final var promosService = new StaticPromosService(Map.of(
                rockyRoad.getId(), new ThirdFreePromo(),
                cookiesAndCream.getId(), new ThirdHalfPricePromo()
        ));

        final var orderCalculator = new OrderCalculator(promosService);

        final var totalOrder = orderCalculator.calculateTotalOrder(PurchaseOrder.builder()
                .purchases(List.of(
                        Purchase.builder().item(rockyRoad).quantity(1).build(),
                        Purchase.builder().item(cookiesAndCream).quantity(3).build(),
                        Purchase.builder().item(netflixXChill).quantity(2).build()
                ))
                .build());

        System.out.println(totalOrder);

    }
}
