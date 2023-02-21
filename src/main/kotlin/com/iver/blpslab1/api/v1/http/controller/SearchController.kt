package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.views.ItemShortView
import com.iver.blpslab1.api.v1.http.views.toShortView
import com.iver.blpslab1.domain.service.item.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchController(
    val itemService: ItemService
) {

    @GetMapping
    fun getAllByKeyword(
        @RequestParam keyword: String
    ): List<ItemShortView> = itemService
        .getAllByKeyword(keyword)
        .map {
            it.toShortView()
        }

    @GetMapping("/hints")
    fun getHints(
        @RequestParam keyword: String
    ): List<ItemShortView> = itemService
        .getHints(keyword)
        .map {
            it.toShortView()
        }
}
