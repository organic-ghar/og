package com.example.og.service

import com.example.og.entities.Product
import com.example.og.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductById(productId: Long): Product? {
        return productRepository.findById(productId).orElse(null)
    }

    fun getProductsByCategory(categoryId: Long): List<Product> {
        return productRepository.findByCategoryId(categoryId)
    }

    fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun updateProduct(productId: Long, updatedProduct: Product): Product? {
        val existingProduct = productRepository.findById(productId).orElse(null) ?: return null
        return productRepository.save(updatedProduct.copy(id = existingProduct.id))
    }

    fun deleteProduct(productId: Long) {
        productRepository.deleteById(productId)
    }
}

