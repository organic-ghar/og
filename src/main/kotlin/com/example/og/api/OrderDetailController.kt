package com.example.og.api

import com.example.og.entities.OrderDetail
import com.example.og.service.OrderDetailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/order-details")
class OrderDetailController(
    private val orderDetailService: OrderDetailService,
) {

    @GetMapping
    fun getAllOrderDetails(): ResponseEntity<List<OrderDetail>> {
        val orderDetails = orderDetailService.getAllOrderDetails()
        return ResponseEntity.ok(orderDetails)
    }

    @GetMapping("/order/{orderId}")
    fun getOrderDetailsByOrder(@PathVariable orderId: UUID): ResponseEntity<List<OrderDetail>> {
        val orderDetails = orderDetailService.getOrderDetailsByOrder(orderId)
        return ResponseEntity.ok(orderDetails)
    }

    @PostMapping
    fun addOrderDetail(@RequestBody orderDetail: OrderDetail): ResponseEntity<OrderDetail> {
        val createdOrderDetail = orderDetailService.addOrderDetail(orderDetail)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail)
    }

    @PutMapping("/{id}")
    fun updateOrderDetail(
        @PathVariable id: UUID,
        @RequestBody updatedOrderDetail: OrderDetail
    ): ResponseEntity<OrderDetail?> {
        val orderDetail = orderDetailService.updateOrderDetail(id, updatedOrderDetail)
        return if (orderDetail != null) {
            ResponseEntity.ok(orderDetail)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOrderDetail(@PathVariable id: UUID): ResponseEntity<Void> {
        orderDetailService.deleteOrderDetail(id)
        return ResponseEntity.noContent().build()
    }
}
