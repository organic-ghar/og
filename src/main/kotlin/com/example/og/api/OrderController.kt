package com.example.og.api

import com.example.og.entities.Order
import com.example.og.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService,
) {

    @GetMapping
    fun getAllOrders(): ResponseEntity<List<Order>> {
        val orders = orderService.getAllOrders()
        return ResponseEntity.ok(orders)
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<Order?> {
        val order = orderService.getOrderById(id)
        return if (order != null) {
            ResponseEntity.ok(order)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun addOrder(@RequestBody order: Order): ResponseEntity<Order> {
        val createdOrder = orderService.addOrder(order)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder)
    }

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: Long, @RequestBody updatedOrder: Order): ResponseEntity<Order?> {
        val order = orderService.updateOrder(id, updatedOrder)
        return if (order != null) {
            ResponseEntity.ok(order)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Void> {
        orderService.deleteOrder(id)
        return ResponseEntity.noContent().build()
    }
}
