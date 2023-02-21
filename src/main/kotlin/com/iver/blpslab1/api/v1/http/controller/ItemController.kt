package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.domain.service.ItemService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v1/item")
class ItemController(
    private val itemService: ItemService
) {

    @PostMapping()
    fun createItem(
        createItemRequest: CreateItemRequest
    ) {
        itemService.createItem(createItemRequest)
    }

    @GetMapping("/{itemId}")
    fun getItemById(
        @PathVariable itemId: Long
    ) {

    }

    @PutMapping("/{itemId}")
    fun updateItem(
        @PathVariable itemId: Long
    ) {

    }

    @DeleteMapping("/{itemId}")
    fun deleteItem(
        @PathVariable itemId: Long
    ) {

    }
}
