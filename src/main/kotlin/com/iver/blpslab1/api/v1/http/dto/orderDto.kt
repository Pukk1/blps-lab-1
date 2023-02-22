package com.iver.blpslab1.api.v1.http.dto

import com.iver.blpslab1.api.v1.http.requests.CreateItemRequest
import com.iver.blpslab1.api.v1.http.requests.toEntity
import com.iver.blpslab1.api.v1.http.views.ItemView
import com.iver.blpslab1.api.v1.http.views.toView
import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RequestOrderDto(
    var id: OrderId,
    var coast: Float,
    @NotBlank
    var phoneNumber: String,
    @Email
    @NotBlank
    var email: String,
    var address: AddressDto,
    var items: List<CreateItemRequest>,
)

data class ResponseOrderDto(
    var id: OrderId,
    var coast: Float,
    @NotBlank
    var phoneNumber: String,
    @Email
    @NotBlank
    var email: String,
    var address: AddressDto,
    var items: List<ItemView>,
)

fun RequestOrderDto.toEntity() = OrderEntity(
    id = id,
    coast = coast,
    phoneNumber = phoneNumber,
    email = email,
    address = address.toEntity(),
    items = items.map { it.toEntity() },
)

fun OrderEntity.toDto() = ResponseOrderDto(
    id = id,
    coast = coast,
    phoneNumber = phoneNumber,
    email = email,
    address = address.toDto(),
    items = items.map { it.toView() }
)
