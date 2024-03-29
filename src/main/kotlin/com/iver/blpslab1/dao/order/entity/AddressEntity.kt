package com.iver.blpslab1.dao.order.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

typealias AddressId = Long

@Entity
class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: AddressId,
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
