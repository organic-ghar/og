package com.example.og.repository

import com.example.og.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    fun findByCategoryId(categoryId: UUID): List<Product>
    fun findByActive(active: Boolean): List<Product>
    @Query("SELECT p FROM Product p WHERE p.featured = true AND p.active = false")
    fun findAllFeaturedProducts(): List<Product>

    @Query("SELECT p FROM Product p ORDER BY p.createdDate DESC")
    fun findNewProducts(): List<Product?>?

}

