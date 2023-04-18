package com.iver.blpslab1.dao.item

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemEntity, Long> {

    @Query(nativeQuery = true, value =  "SELECT * FROM item_entity WHERE full_name LIKE %:keyword%")
    fun findSimilar(
        keyword: String,
        pageable: Pageable,
    ): Page<ItemEntity>

    @Query(nativeQuery = true, value =  "SELECT * FROM item_entity WHERE full_name LIKE %:keyword% LIMIT :limit")
    fun findSimilarWithLimit(
        keyword: String,
        limit: Int
    ): List<ItemEntity>
}
