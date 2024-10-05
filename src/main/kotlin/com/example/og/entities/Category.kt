package com.example.og.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val description: String,

    @ManyToOne
    @JoinColumn(name = "cat_type_id")
    val catType: Lookup,
): BaseEntity()

