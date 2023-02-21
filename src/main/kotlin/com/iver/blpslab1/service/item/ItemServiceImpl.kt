package com.iver.blpslab1.service.item

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.dao.item.entity.ItemEntity
import com.iver.blpslab1.dao.item.repository.ItemRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository
) : ItemService {
    override fun createItem(
        request: CreateItemRequest
    ): ItemEntity = save(request.toEntity())

    override fun getItemById(
        id: Long
    ): ItemEntity? = itemRepository.findByIdOrNull(id)

    override fun updateItem(
        id: Long,
        request: UpdateItemRequest
    ): ItemEntity {
        val item = itemRepository.findById(id).orElseThrow { EntityNotFoundException() }
        return save(request.toEntity(item.id))
    }

    override fun deleteItem(
        id: Long
    ) = itemRepository.deleteById(id)

    fun getAllByKeyword(
        keyword: String
    ): List<ItemEntity> = itemRepository.findSimilar(keyword)

    private val hintSearchingLimit = 5
    fun getHints(
        keyword: String
    ): List<ItemEntity> = itemRepository.findSimilarWithLimit(keyword, hintSearchingLimit)

    private fun save(
        itemEntity: ItemEntity
    ): ItemEntity = itemRepository.save(itemEntity)
}
