package com.iver.blpslab1.dao.order.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

typealias AdressId = Long

@Entity
class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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