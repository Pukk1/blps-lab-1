package com.iver.blpslab1.service.item

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.dao.item.entity.ItemEntity

interface ItemService {

    fun createItem(
        request: CreateItemRequest
    ): ItemEntity
    fun getItemById(
        id: Long
    ): ItemEntity?
    fun updateItem(
        id: Long,
        request: UpdateItemRequest
    ): ItemEntity
    fun deleteItem(
        id: Long
    )
}
