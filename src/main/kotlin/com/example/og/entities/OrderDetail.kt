package com.example.og.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
data class OrderDetail(
    @Id 
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "shipment_status_id")
    val shipmentStatus: Lookup,

    @ManyToOne
    @JoinColumn(name = "payment_status_id")
    val paymentStatus: Lookup,

    val deliveredDate: LocalDateTime?,
): BaseEntity()
