package com.iver.blpslab1.api.v1.http.controller.dto

import com.iver.blpslab1.dao.order.entity.AddressEntity
import com.iver.blpslab1.dao.order.entity.OrderId
import jakarta.persistence.OneToOne
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
    @OneToOne
    var addressEntity: AddressEntity,
)