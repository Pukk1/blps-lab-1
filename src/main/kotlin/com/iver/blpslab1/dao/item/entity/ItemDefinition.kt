package com.iver.blpslab1.dao.item.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ItemDefinition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long
)