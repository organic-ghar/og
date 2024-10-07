package com.example.og.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Lookup(
    @Id 
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    val name: String,
    val description: String?,
    val value: String,
    val active: Boolean,
): BaseEntity()

