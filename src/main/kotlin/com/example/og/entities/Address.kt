package com.example.og.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Address(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

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

