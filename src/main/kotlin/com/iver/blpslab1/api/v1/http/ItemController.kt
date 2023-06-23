package com.iver.blpslab1.api.v1.http

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.UpdateItemRequest
import com.iver.blpslab1.api.v1.http.views.ItemView
import com.iver.blpslab1.api.v1.http.views.toView
import com.iver.blpslab1.service.item.ItemService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/api/v1/item")
class ItemController(
    private val itemService: ItemService
) {

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun createItem(
//        @RequestBody createItemRequest: CreateItemRequest,
//    ): ItemView = itemService.createItem(createItemRequest).toView()

//    @GetMapping("/{itemId}")
//    fun getItemById(
//        @PathVariable itemId: Long,
//    ): ItemView = itemService.getItemById(itemId).toView()

    @PutMapping("/{itemId}")
    fun updateItem(
        @PathVariable itemId: Long,
        @RequestBody updateItemRequest: UpdateItemRequest,
    ): ItemView = itemService.updateItem(itemId, updateItemRequest).toView()

    @DeleteMapping("/{itemId}")
    fun deleteItem(
        @PathVariable itemId: Long,
    ) = itemService.deleteItem(itemId)
}
