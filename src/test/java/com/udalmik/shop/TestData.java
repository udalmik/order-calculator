package com.udalmik.shop;

import com.udalmik.shop.model.Item;
import com.udalmik.shop.promo.StaticPromosService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestData {

    public static OrderCalculator noPromosCalculator() {
        return new OrderCalculator(new StaticPromosService());
    }

    public static List<Item> createItemsOfPrices(List<BigDecimal> prices) {
        return IntStream
                .range(0, prices.size())
                .mapToObj(idx -> Item.builder().id(idx).name("Test" + idx).price(prices.get(idx)).build())
                .collect(Collectors.toList());
    }

}
