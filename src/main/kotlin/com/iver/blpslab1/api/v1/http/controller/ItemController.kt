package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.controller.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.controller.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.controller.views.ItemView
import com.iver.blpslab1.api.v1.http.controller.views.toView
import com.iver.blpslab1.domain.service.ItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/item")
class ItemController(
    private val itemService: ItemService
) {

    @PostMapping
    fun createItem(
        @RequestBody createItemRequest: CreateItemRequest
    ): ItemView = itemService.createItem(createItemRequest).toView()

    @GetMapping("/{itemId}")
    fun getItemById(
        @PathVariable itemId: Long
    ): ItemView? = itemService.getItemById(itemId)?.toView()

    @PutMapping("/{itemId}")
    fun updateItem(
        @PathVariable itemId: Long,
        @RequestBody updateItemRequest: UpdateItemRequest
    ): ItemView = itemService.updateItem(itemId, updateItemRequest).toView()

    @DeleteMapping("/{itemId}")
    fun deleteItem(
        @PathVariable itemId: Long
    ) = itemService.deleteItem(itemId)
}
