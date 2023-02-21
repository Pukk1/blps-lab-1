package com.iver.blpslab1.api.v1.http.dto

import com.iver.blpslab1.dao.order.entity.AddressEntity
import com.iver.blpslab1.dao.order.entity.AddressId
import jakarta.validation.constraints.NotBlank

data class AddressDto(
    var id: AddressId,
    @NotBlank
    var country: String,
    @NotBlank
    var city: String,
    @NotBlank
    var street: String,
    var homeNumber: Int,
    var flat: Int,
    var comment: String?,
)

fun AddressDto.toEntity() = AddressEntity(
    id = id,
    country = country,
    city = city,
    street = street,
    homeNumber = homeNumber,
    flat = flat,
    comment = comment,
)

fun AddressEntity.toDto() = AddressDto(
    id = id,
    country = country,
    city = city,
    street = street,
    homeNumber = homeNumber,
    flat = flat,
    comment = comment,
)


