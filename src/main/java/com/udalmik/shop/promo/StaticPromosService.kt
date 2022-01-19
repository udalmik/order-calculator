package com.udalmik.shop.promo

class StaticPromosService(private val itemPromosMap: Map<Long, ItemPromo> = emptyMap()) : PromosService {
    override fun getItemPromo(itemId: Long): ItemPromo? {
        return itemPromosMap[itemId]
    }
}