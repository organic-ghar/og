package com.example.og.repository

import com.example.og.entities.ProductReview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductReviewRepository : JpaRepository<ProductReview, Long> {
    fun findByProductId(productId: Long): List<ProductReview>
    fun findByCustomerId(customerId: Long): List<ProductReview>
}

