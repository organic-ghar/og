package com.example.og.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "customer_order")
data class Order(
    @Id 
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    val orderDate: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "address_id")
    val address: Address,

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    val orderStatus: Lookup,

    @ManyToOne
    @JoinColumn(name = "payment_status_id")
    val paymentStatus: Lookup,
): BaseEntity()

