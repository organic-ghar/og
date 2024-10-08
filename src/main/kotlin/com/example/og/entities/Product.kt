package com.example.og.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Product(
    @Id 
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    val name: String,
    val description: String,
    val price: Double,
    val featured: Boolean? = false,

    @ManyToOne
    @JoinColumn(name = "offer_type_id")
    val offerType: Lookup? = null,

    val active: Boolean,
): BaseEntity()

