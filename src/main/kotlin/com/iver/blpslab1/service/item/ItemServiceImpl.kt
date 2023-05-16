package com.iver.blpslab1.service.item

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.dao.item.ItemEntity
import com.iver.blpslab1.dao.item.ItemRepository
import com.iver.blpslab1.exception.NotFoundException
import com.iver.blpslab1.service.utils.doTransaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository,
    transactionManager: PlatformTransactionManager,
) : ItemService, ItemSearchService {

    private val transactionTemplate = TransactionTemplate(transactionManager)

    override fun createItem(
        request: CreateItemRequest
    ): ItemEntity =
        doTransaction(transactionTemplate = transactionTemplate) {
            save(request.toEntity())
        }

    override fun getItemById(
        id: Long
    ): ItemEntity = itemRepository.findById(id).orElseThrow { NotFoundException("Item not found") }

    override fun updateItem(
        id: Long,
        request: UpdateItemRequest
    ): ItemEntity =
        doTransaction(transactionTemplate = transactionTemplate) {
            val item = itemRepository.findById(id).orElseThrow { NotFoundException("Item not found") }
            save(request.toEntity(item.id))
        }

    override fun deleteItem(
        id: Long
    ) {
        doTransaction(transactionTemplate = transactionTemplate) {
            itemRepository.deleteById(id)
        }
    }

    override fun getAllByKeyword(
        keyword: String,
        pageable: Pageable,
    ): Page<ItemEntity> = itemRepository.findSimilar(keyword, pageable)

    private val hintSearchingLimit = 5
    override fun getHints(
        keyword: String
    ): List<ItemEntity> = itemRepository.findSimilarWithLimit(keyword, hintSearchingLimit)

    override fun orderNewItemsMessage() {
        if (checkItemsInStock()) {
            println("There is no need to buy new items yet")
        } else {
            sendMessageToOrderNewItems()
            println("Message about the need to purchase new items has been sent")
        }
    }

    private fun checkItemsInStock(): Boolean {
        val inStockItems = itemRepository.findByInStockTrue()
        val notInStockItems = itemRepository.findByInStockFalse()

        return if (inStockItems.isNotEmpty()) {
            notInStockItems.size / (notInStockItems.size + inStockItems.size) < 0.2
        } else false
    }

    private fun sendMessageToOrderNewItems() {
        println("The items are running out, order new ones!")
    }

    private fun save(
        itemEntity: ItemEntity
    ): ItemEntity = itemRepository.save(itemEntity)
}
