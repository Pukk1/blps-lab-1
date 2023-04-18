package com.iver.blpslab1.service.item

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.dao.item.ItemEntity
import com.iver.blpslab1.dao.item.ItemRepository
import com.iver.blpslab1.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository
) : ItemService, ItemSearchService {

    override fun createItem(
        request: CreateItemRequest
    ): ItemEntity = save(request.toEntity())

    override fun getItemById(
        id: Long
    ): ItemEntity = itemRepository.findById(id).orElseThrow { NotFoundException("Item not found") }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun updateItem(
        id: Long,
        request: UpdateItemRequest
    ): ItemEntity {
        val item = itemRepository.findById(id).orElseThrow { NotFoundException("Item not found") }
        return save(request.toEntity(item.id))
    }

    override fun deleteItem(
        id: Long
    ) = itemRepository.deleteById(id)

    override fun getAllByKeyword(
        keyword: String,
        pageable: Pageable,
    ): Page<ItemEntity> = itemRepository.findSimilar(keyword, pageable)

    private val hintSearchingLimit = 5
    override fun getHints(
        keyword: String
    ): List<ItemEntity> = itemRepository.findSimilarWithLimit(keyword, hintSearchingLimit)

    private fun save(
        itemEntity: ItemEntity
    ): ItemEntity = itemRepository.save(itemEntity)
}
