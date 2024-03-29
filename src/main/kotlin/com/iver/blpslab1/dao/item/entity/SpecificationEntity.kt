package com.iver.blpslab1.dao.item.entity

import jakarta.persistence.*

@Entity
class SpecificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val value: String,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: ItemEntity,
)
