package com.udalmik.shop;

import com.udalmik.shop.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestData {

    public static List<Item> createItemsOfPrices(List<BigDecimal> prices) {
        return IntStream
                .range(0, prices.size())
                .mapToObj(idx -> createItem(idx, prices.get(idx)))
                .collect(Collectors.toList());
    }

    public static Item createItem(long id, BigDecimal price) {
        return Item.builder().id(id).name("Test" + id).price(price).build();
    }

    public static Item createItemOfPrice(BigDecimal price) {
        return createItem(0, price);
    }

}
