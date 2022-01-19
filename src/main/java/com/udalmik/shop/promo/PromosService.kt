package com.udalmik.shop.promo

interface PromosService {
    fun getItemPromo(itemId: Long): ItemPromo?
}