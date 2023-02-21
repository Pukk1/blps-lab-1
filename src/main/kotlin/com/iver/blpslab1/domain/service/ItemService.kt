package com.iver.blpslab1.domain.service

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.dao.item.entity.ItemEntity
import com.iver.blpslab1.dao.item.repository.ItemRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    fun createItem(
        request: CreateItemRequest
    ): ItemEntity = save(request.toEntity())

    fun getItemById(
        id: Long
    ): ItemEntity? = itemRepository.findByIdOrNull(id)

    fun updateItem(
        id: Long,
        request: UpdateItemRequest
    ): ItemEntity {
        val item = itemRepository.findById(id).orElseThrow { EntityNotFoundException() }
        return save(request.toEntity(item.id))
    }

    fun deleteItem(
        id: Long
    ) {
        itemRepository.deleteById(id)
    }

    private fun save(
        itemEntity: ItemEntity
    ): ItemEntity = itemRepository.save(itemEntity)
}
