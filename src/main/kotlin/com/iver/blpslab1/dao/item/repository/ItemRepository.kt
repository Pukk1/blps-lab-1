package com.iver.blpslab1.dao.item.repository

import com.iver.blpslab1.dao.item.entity.ItemEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<ItemEntity, Long>