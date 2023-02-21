package com.iver.blpslab1.api.v1.http.controller.views

import com.iver.blpslab1.dao.item.entity.ItemEntity

class ItemView(
    val id: Long,
    val fullName: String,
    val inStock: Boolean,
    val price: Int?,
    val warrantyPeriod: Int?,
    val country: String,
    val definition: String,
)

fun ItemEntity.toView(
): ItemView = ItemView(
    id = id,
    fullName = fullName,
    inStock = inStock,
    price = price,
    warrantyPeriod = warrantyPeriod,
    country = country,
    definition = definition
)
