package com.udalmik.shop.promo;

import java.util.Optional;

public interface PromosService {

    Optional<ItemPromo> getItemPromo(long itemId);
}
