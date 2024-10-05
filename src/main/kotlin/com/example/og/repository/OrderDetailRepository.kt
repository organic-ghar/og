package com.example.og.repository

import com.example.og.entities.OrderDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderDetailRepository : JpaRepository<OrderDetail, UUID> {
    fun findByOrderId(orderId: UUID): List<OrderDetail>
}

