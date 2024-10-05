package com.example.og.repository

import com.example.og.entities.OrderDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailRepository : JpaRepository<OrderDetail, Long> {
    fun findByOrderId(orderId: Long): List<OrderDetail>
}

