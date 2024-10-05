package com.example.og.repository

import com.example.og.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByCategoryId(categoryId: Long): List<Product>
    fun findByActive(active: Boolean): List<Product>
}

