package com.example.og.api

import com.example.og.entities.ProductReview
import com.example.og.service.ProductReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/reviews")
class ProductReviewController(
    private val productReviewService: ProductReviewService,
) {

    @GetMapping
    fun getAllReviews(): ResponseEntity<List<ProductReview>> {
        val reviews = productReviewService.getAllReviews()
        return ResponseEntity.ok(reviews)
    }

    @GetMapping("/product/{productId}")
    fun getReviewsByProduct(@PathVariable productId: UUID): ResponseEntity<List<ProductReview>> {
        val reviews = productReviewService.getReviewsByProduct(productId)
        return ResponseEntity.ok(reviews)
    }

    @GetMapping("/customer/{customerId}")
    fun getReviewsByCustomer(@PathVariable customerId: UUID): ResponseEntity<List<ProductReview>> {
        val reviews = productReviewService.getReviewsByCustomer(customerId)
        return ResponseEntity.ok(reviews)
    }

    @PostMapping
    fun addReview(@RequestBody review: ProductReview): ResponseEntity<ProductReview> {
        val createdReview = productReviewService.addReview(review)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview)
    }

    @PutMapping("/{id}")
    fun updateReview(
        @PathVariable id: UUID,
        @RequestBody updatedReview: ProductReview
    ): ResponseEntity<ProductReview?> {
        val review = productReviewService.updateReview(id, updatedReview)
        return if (review != null) {
            ResponseEntity.ok(review)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteReview(@PathVariable id: UUID): ResponseEntity<Void> {
        productReviewService.deleteReview(id)
        return ResponseEntity.noContent().build()
    }
}
