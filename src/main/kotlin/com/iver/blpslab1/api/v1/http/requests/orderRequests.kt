package com.iver.blpslab1.api.v1.http.requests

import com.iver.blpslab1.dao.order.entity.AddressEntity
import com.iver.blpslab1.dao.order.entity.AddressId
import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class OrderRequest(
    var id: OrderId,
    var coast: Float,
    @NotBlank
    var phoneNumber: String,
    @Email
    @NotBlank
    var email: String,
    var address: AddressRequest,
    var items: List<CreateItemRequest>,
)

data class AddressRequest(
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

fun OrderRequest.toEntity() = OrderEntity(
    id = id,
    coast = coast,
    phoneNumber = phoneNumber,
    email = email,
    address = address.toEntity(),
    items = items.map { it.toEntity() },
)

fun AddressRequest.toEntity() = AddressEntity(
    id = id,
    country = country,
    city = city,
    street = street,
    homeNumber = homeNumber,
    flat = flat,
    comment = comment,
)
