package com.udalmik.shop.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Item {
    private final long id;
    private final String name;
    private final BigDecimal price;
}
