package com.iver.blpslab1.domain.service

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.dao.item.repository.ItemRepository
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    fun createItem(
        createItemRequest: CreateItemRequest
    ) {

    }
}