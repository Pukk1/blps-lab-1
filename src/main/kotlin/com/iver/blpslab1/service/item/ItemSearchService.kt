package com.iver.blpslab1.service.item

import com.iver.blpslab1.dao.item.entity.ItemEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ItemSearchService {

    fun getAllByKeyword(
        keyword: String,
        pageable: Pageable,
    ): Page<ItemEntity>

    fun getHints(
        keyword: String
    ): List<ItemEntity>
}
