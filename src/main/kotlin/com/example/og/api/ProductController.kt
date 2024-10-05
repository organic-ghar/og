package com.example.og.api

import com.example.og.entities.Product
import com.example.og.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: UUID): ResponseEntity<Product?> {
        val product = productService.getProductById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> {
        val createdProduct = productService.addProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: UUID, @RequestBody updatedProduct: Product): ResponseEntity<Product?> {
        val product = productService.updateProduct(id, updatedProduct)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }
}
