package com.example.og.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "customer_order")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

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

