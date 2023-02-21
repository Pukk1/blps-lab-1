package com.iver.blpslab1.dao.item.repository

import com.iver.blpslab1.dao.item.entity.ItemEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<ItemEntity, Long> {

    @Query(nativeQuery = true, value =  "SELECT * FROM item_entity WHERE full_name LIKE %:keyword%")
    fun findSimilar(
        keyword: String
    ): List<ItemEntity>

    @Query(nativeQuery = true, value =  "SELECT * FROM item_entity WHERE full_name LIKE %:keyword% LIMIT :limit")
    fun findSimilarWithLimit(
        keyword: String,
        limit: Int
    ): List<ItemEntity>
}
