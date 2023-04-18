package com.iver.blpslab1.dao.order

import com.iver.blpslab1.dao.item.ItemEntity
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

typealias OrderId = Long

@Entity
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: OrderId,
    val coast: Float,
    @NotBlank
    val phoneNumber: String,
    @Email
    @NotBlank
    val email: String,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id")
    val address: AddressEntity,
    @ManyToMany
    @JoinTable(
        name = "order_item",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "course_id")]
    )
    val items: List<ItemEntity>,
)