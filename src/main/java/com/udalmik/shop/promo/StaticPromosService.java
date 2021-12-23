package com.udalmik.shop.promo;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class StaticPromosService implements PromosService {

    private final Map<Long, ItemPromo> itemPromoMap;

    @Override
    public Optional<ItemPromo> getItemPromo(long itemId) {
        return Optional.ofNullable(itemPromoMap.get(itemId));
    }
}