package com.example.og.repository

import com.example.og.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    fun findByCategoryId(categoryId: UUID): List<Product>
    fun findByActive(active: Boolean): List<Product>
}

