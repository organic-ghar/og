package com.example.og.service

import com.example.og.entities.Order
import com.example.og.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {

    fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }

    fun getOrderById(orderId: UUID): Order? {
        return orderRepository.findById(orderId).orElse(null)
    }

    fun getOrdersByCustomer(customerId: UUID): List<Order> {
        return orderRepository.findByCustomerId(customerId)
    }

    fun addOrder(order: Order): Order {
        return orderRepository.save(order)
    }

    fun updateOrder(orderId: UUID, updatedOrder: Order): Order? {
        val existingOrder = orderRepository.findById(orderId).orElse(null) ?: return null
        return orderRepository.save(updatedOrder.copy(id = existingOrder.id))
    }

    fun deleteOrder(orderId: UUID) {
        orderRepository.deleteById(orderId)
    }
}
