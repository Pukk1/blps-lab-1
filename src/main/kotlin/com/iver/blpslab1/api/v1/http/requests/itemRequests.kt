package com.iver.blpslab1.api.v1.http.requests

import com.iver.blpslab1.dao.item.ItemEntity
import javax.validation.constraints.NotBlank

class CreateItemRequest(
    @NotBlank
    val fullName: String,
    val inStock: Boolean,
    val price: Int?,
    val warrantyPeriod: Int?,
    @NotBlank
    val country: String,
    @NotBlank
    val definition: String?,
)

class UpdateItemRequest(
    @NotBlank
    val fullName: String,
    val inStock: Boolean,
    val price: Int?,
    val warrantyPeriod: Int?,
    @NotBlank
    val country: String,
    @NotBlank
    val definition: String?,
)

fun CreateItemRequest.toEntity(
): ItemEntity = ItemEntity(
    id = 0L,
    fullName = fullName,
    inStock = inStock,
    price = price,
    warrantyPeriod = warrantyPeriod,
    country = country,
    definition = definition,
    specifications = listOf(),
)


fun UpdateItemRequest.toEntity(
    id: Long
): ItemEntity = ItemEntity(
    id = id,
    fullName = fullName,
    inStock = inStock,
    price = price,
    warrantyPeriod = warrantyPeriod,
    country = country,
    definition = definition,
    specifications = listOf(),
)
