package com.example.og.api

import com.example.og.entities.Category
import com.example.og.entities.Product
import com.example.og.service.CategoryService
import com.example.og.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/home")
class HomeController(
    private val productService: ProductService,
    private val categoryService: CategoryService,
) {

    @GetMapping
    fun getHomeProducts(): HomeResponseDto {
        return HomeResponseDto(
            productService.getFeaturedProducts(),
            emptyList(),
            productService.getNewProducts(),
            categoryService.getAllCategories()
        )
    }

    data class HomeResponseDto(
        val featuredProducts: List<Product>,
        val bundleOffers: List<Product>,
        val newProducts: List<Product?>,
        val categories: List<Category>,
    )
}
