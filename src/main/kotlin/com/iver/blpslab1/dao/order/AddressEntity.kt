package com.iver.blpslab1.dao.order

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

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