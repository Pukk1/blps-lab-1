package com.iver.blpslab1.api.v1.http.views

import com.iver.blpslab1.dao.order.entity.OrderEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class OrderView(
    var id: OrderId,
    var coast: Float,
    @NotBlank
    var phoneNumber: String,
    @Email
    @NotBlank
    var email: String,
    var address: AddressView,
    var items: List<ItemView>,
)

fun OrderEntity.toView() = OrderView(
    id = id,
    coast = coast,
    phoneNumber = phoneNumber,
    email = email,
    address = address.toView(),
    items = items.map { it.toView() }
)
