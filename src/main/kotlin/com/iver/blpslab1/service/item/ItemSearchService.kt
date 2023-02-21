package com.iver.blpslab1.service.item

import com.iver.blpslab1.dao.item.entity.ItemEntity

interface ItemSearchService {

    fun getAllByKeyword(
        keyword: String
    ): List<ItemEntity>

    fun getHints(
        keyword: String
    ): List<ItemEntity>
}