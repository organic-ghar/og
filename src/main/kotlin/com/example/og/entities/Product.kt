package com.example.og.entities

import jakarta.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    val name: String,
    val description: String,
    val price: Double,

    @ManyToOne
    @JoinColumn(name = "offer_type_id")
    val offerType: Lookup,

    val active: Boolean,
): BaseEntity()

