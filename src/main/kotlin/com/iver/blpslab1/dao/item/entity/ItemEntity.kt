package com.iver.blpslab1.dao.item.entity

import jakarta.persistence.*

@Entity
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val fullName: String,
    val price: Int,

    val warrantyPeriod: Int,
)