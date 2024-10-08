package com.example.og.service

import com.example.og.configs.CategoryAndProductInitializer
import com.example.og.entities.Category
import com.example.og.entities.Lookup
import com.example.og.entities.Product
import com.example.og.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService,
    private val lookupService: LookupService,
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


    fun deleteAll() {
        productRepository.deleteAll()
    }

    fun saveProducts(
        products: List<CategoryAndProductInitializer.ProductDto>,
    ): List<Product> {
        val productEntities = mutableListOf<Product>()

        for (dto in products) {
            val category = categoryService.getCategoryById(dto.categoryId)
                ?: throw IllegalArgumentException("Category not found for ID: ${dto.categoryId}")

            val product = mapToProduct(dto, category, null)
            productEntities.add(product)
        }
        return productRepository.saveAll(productEntities)
    }

    // may be we can use a default offer type on the initialization
    private fun mapToProduct(dto: CategoryAndProductInitializer.ProductDto, category: Category, offerType: Lookup?): Product {
        return Product(
            category = category,
            name = dto.name,
            description = dto.description,
            price = dto.price,
            active = true // Assuming all products are active by default
        ).apply { createdBy = "admin" }
    }
}

