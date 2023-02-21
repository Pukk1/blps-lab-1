package com.iver.blpslab1.api.v1.http.dto

import com.iver.blpslab1.api.v1.http.views.ItemView
import com.iver.blpslab1.dao.order.entity.OrderId
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class OrderDto(
    val id: OrderId,
    val coast: Float,
    @NotBlank
    val phoneNumber: String,
    @Email
    @NotBlank
    val email: String,
    val address: AddressDto,
    val items: List<ItemView>,
)