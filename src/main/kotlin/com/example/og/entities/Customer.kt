package com.example.og.entities

import jakarta.persistence.*

@Entity
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "cust_type_id")
    val customerType: Lookup,

    val active: Boolean,
): BaseEntity()

