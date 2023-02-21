package com.iver.blpslab1.api.v1.http.controller.dto

import com.iver.blpslab1.dao.order.entity.AdressId
import jakarta.validation.constraints.NotBlank

class AddressDto(
    val id: AdressId,
    @NotBlank
    val country: String,
    @NotBlank
    val city: String,
    @NotBlank
    val street: String,
    val homeNumber: Int,
    val flat: Int,
    val comment: String?,
)