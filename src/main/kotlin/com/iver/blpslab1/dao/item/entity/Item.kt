package com.iver.blpslab1.dao.item.entity

import jakarta.persistence.*

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var warrantyPeriod: Int
)