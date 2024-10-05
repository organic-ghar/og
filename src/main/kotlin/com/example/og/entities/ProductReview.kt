package com.example.og.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class ProductReview(
    @Id 
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    val comments: String,

    @ManyToOne
    @JoinColumn(name = "rating_id")
    val rating: Lookup,
): BaseEntity()

