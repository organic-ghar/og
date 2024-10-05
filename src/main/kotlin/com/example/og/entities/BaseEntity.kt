package com.example.og.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    @Column(nullable = false, updatable = false)
    var createdBy: String? = null,

    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = true)
    var updatedBy: String? = null,

    @Column(nullable = true)
    var updatedDate: LocalDateTime? = null
)

