package com.example.og.service

import com.example.og.entities.OrderDetail
import com.example.og.repository.OrderDetailRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderDetailService(
    private val orderDetailRepository: OrderDetailRepository,
) {

    fun getAllOrderDetails(): List<OrderDetail> {
        return orderDetailRepository.findAll()
    }

    fun getOrderDetailsByOrder(orderId: UUID): List<OrderDetail> {
        return orderDetailRepository.findByOrderId(orderId)
    }

    fun addOrderDetail(orderDetail: OrderDetail): OrderDetail {
        return orderDetailRepository.save(orderDetail)
    }

    fun updateOrderDetail(orderDetailId: UUID, updatedOrderDetail: OrderDetail): OrderDetail? {
        val existingOrderDetail = orderDetailRepository.findById(orderDetailId).orElse(null) ?: return null
        return orderDetailRepository.save(updatedOrderDetail.copy(id = existingOrderDetail.id))
    }

    fun deleteOrderDetail(orderDetailId: UUID) {
        orderDetailRepository.deleteById(orderDetailId)
    }
}
