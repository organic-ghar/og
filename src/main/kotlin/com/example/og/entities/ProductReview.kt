package com.example.og.entities

import jakarta.persistence.*

@Entity
data class ProductReview(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

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

