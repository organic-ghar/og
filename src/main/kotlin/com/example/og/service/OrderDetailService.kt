package com.example.og.service

import com.example.og.entities.OrderDetail
import com.example.og.repository.OrderDetailRepository
import org.springframework.stereotype.Service

@Service
class OrderDetailService(
    private val orderDetailRepository: OrderDetailRepository,
) {

    fun getAllOrderDetails(): List<OrderDetail> {
        return orderDetailRepository.findAll()
    }

    fun getOrderDetailsByOrder(orderId: Long): List<OrderDetail> {
        return orderDetailRepository.findByOrderId(orderId)
    }

    fun addOrderDetail(orderDetail: OrderDetail): OrderDetail {
        return orderDetailRepository.save(orderDetail)
    }

    fun updateOrderDetail(orderDetailId: Long, updatedOrderDetail: OrderDetail): OrderDetail? {
        val existingOrderDetail = orderDetailRepository.findById(orderDetailId).orElse(null) ?: return null
        return orderDetailRepository.save(updatedOrderDetail.copy(id = existingOrderDetail.id))
    }

    fun deleteOrderDetail(orderDetailId: Long) {
        orderDetailRepository.deleteById(orderDetailId)
    }
}
