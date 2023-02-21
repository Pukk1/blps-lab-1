package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.views.ItemView
import com.iver.blpslab1.api.v1.http.views.toView
import com.iver.blpslab1.service.item.ItemServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/item")
class ItemController(
    private val itemService: ItemServiceImpl
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
