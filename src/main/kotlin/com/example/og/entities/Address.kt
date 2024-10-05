package com.example.og.entities

import jakarta.persistence.*

@Entity
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    val addressLine1: String,
    val addressLine2: String?,
    val state: String,
    val pinCode: String,
    val isDefault: Boolean,
    val active: Boolean,
) : BaseEntity()

