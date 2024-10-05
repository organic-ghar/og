package com.example.og.service

import com.example.og.entities.ProductReview
import com.example.og.repository.ProductReviewRepository
import org.springframework.stereotype.Service

@Service
class ProductReviewService(private val productReviewRepository: ProductReviewRepository) {

    fun getAllReviews(): List<ProductReview> {
        return productReviewRepository.findAll()
    }

    fun getReviewsByProduct(productId: Long): List<ProductReview> {
        return productReviewRepository.findByProductId(productId)
    }

    fun getReviewsByCustomer(customerId: Long): List<ProductReview> {
        return productReviewRepository.findByCustomerId(customerId)
    }

    fun addReview(review: ProductReview): ProductReview {
        return productReviewRepository.save(review)
    }

    fun updateReview(reviewId: Long, updatedReview: ProductReview): ProductReview? {
        val existingReview = productReviewRepository.findById(reviewId).orElse(null) ?: return null
        return productReviewRepository.save(updatedReview.copy(id = existingReview.id))
    }

    fun deleteReview(reviewId: Long) {
        productReviewRepository.deleteById(reviewId)
    }
}
