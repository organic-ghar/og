package com.example.og.repository

import com.example.og.entities.ProductReview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductReviewRepository : JpaRepository<ProductReview, UUID> {
    fun findByProductId(productId: UUID): List<ProductReview>
    fun findByCustomerId(customerId: UUID): List<ProductReview>
}

