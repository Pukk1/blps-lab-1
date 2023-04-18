package com.iver.blpslab1.dao.item

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @NotBlank
    val fullName: String,
    val inStock: Boolean,
    val price: Int?,
    val warrantyPeriod: Int?,
    @NotBlank
    val country: String,
    @NotBlank
    val definition: String?,

    @OneToMany(
        mappedBy = "item",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val specifications: List<SpecificationEntity>,
)
