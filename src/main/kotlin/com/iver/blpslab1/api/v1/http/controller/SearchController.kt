package com.iver.blpslab1.api.v1.http.controller

import com.iver.blpslab1.api.v1.http.views.ItemShortView
import com.iver.blpslab1.api.v1.http.views.toShortView
import com.iver.blpslab1.service.item.ItemSearchService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchController(
    val itemSearchService: ItemSearchService
) {

    @GetMapping
    fun getAllByKeyword(
        @RequestParam keyword: String,
        pageable: Pageable,
    ): Page<ItemShortView> = itemSearchService
        .getAllByKeyword(keyword, pageable)
        .map {
            it.toShortView()
        }

    @GetMapping("/hints")
    fun getHints(
        @RequestParam keyword: String
    ): List<ItemShortView> = itemSearchService
        .getHints(keyword)
        .map {
            it.toShortView()
        }
}
