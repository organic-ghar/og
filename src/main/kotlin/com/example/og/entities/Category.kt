package com.example.og.entities

import jakarta.persistence.*
import java.util.*

@Entity
data class Category(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    val name: String,
    val description: String,
    val parentId: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "cat_type_id")
    val catType: Lookup? = null,
) : BaseEntity()

