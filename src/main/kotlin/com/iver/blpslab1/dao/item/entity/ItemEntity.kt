package com.iver.blpslab1.dao.item.entity

import jakarta.persistence.*

@Entity
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val fullName: String,
    val inStock: Boolean,
    val price: Int?,
    val warrantyPeriod: Int?,
    val country: String,
    val definition: String,

    @OneToMany(
        mappedBy = "item",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val specifications: List<Specification>,
)
