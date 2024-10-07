package com.example.og.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Customer(
    @Id 
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    val name: String,

    @ManyToOne
    @JoinColumn(name = "cust_type_id")
    val customerType: Lookup,

    val active: Boolean,
): BaseEntity()

