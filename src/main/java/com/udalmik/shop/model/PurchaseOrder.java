package com.udalmik.shop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PurchaseOrder {
    private final List<Purchase> purchases;
}
