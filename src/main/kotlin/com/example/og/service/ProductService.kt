package com.example.og.service

import com.example.og.entities.Product
import com.example.og.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductById(productId: UUID): Product? {
        return productRepository.findById(productId).orElse(null)
    }

    fun getProductsByCategory(categoryId: UUID): List<Product> {
        return productRepository.findByCategoryId(categoryId)
    }

    fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun updateProduct(productId: UUID, updatedProduct: Product): Product? {
        val existingProduct = productRepository.findById(productId).orElse(null) ?: return null
        return productRepository.save(updatedProduct.copy(id = existingProduct.id))
    }

    fun deleteProduct(productId: UUID) {
        productRepository.deleteById(productId)
    }
}

