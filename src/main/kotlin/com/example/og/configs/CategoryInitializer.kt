package com.example.og.configs

import com.example.og.entities.Category
import com.example.og.entities.Product
import com.example.og.service.CategoryService
import com.example.og.service.ProductService
import com.example.og.utils.FileResourceLoader
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.core.annotation.Order
import org.springframework.core.io.Resource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

@Configuration
@Profile("data-deployment")
class CategoryAndProductInitializer(
    private val categoryService: CategoryService,
    private val productService: ProductService,
    private val fileResourceLoader: FileResourceLoader,
) {
    private val logger = LoggerFactory.getLogger(CategoryAndProductInitializer::class.java)!!
    private val idToUuidMap = mutableMapOf<String, UUID>()  // Store mapping of CSV ID to UUID for categories

    @EventListener(ApplicationReadyEvent::class)
    @Order(10)
    fun onStartup() {
        logger.info("clearing category and product repos")
        productService.deleteAll()
        categoryService.deleteAll()
        logger.info("Starting category and product initialization")

        // Load and process categories first
        val categoryFiles = fileResourceLoader.loadFiles("test-data/category.csv")
        categoryFiles.forEach { file -> processCategoryFile(file) }

        // Load and process products after categories
        val productFiles = fileResourceLoader.loadFiles("test-data/product.csv")
        productFiles.forEach { file -> processProductFile(file) }
    }

    private fun processCategoryFile(file: Resource): Boolean {
        logger.info("Importing categories from file: ${file.filename}")
        val categories = mutableListOf<Category>()

        try {
            val reader = BufferedReader(InputStreamReader(file.inputStream))
            reader.useLines { lines ->
                lines.drop(1)  // Skip the header
                    .forEach { line ->
                        val columns = line.split(",")
                        if (columns.size >= 4) {
                            val id = UUID.randomUUID()
                            val csvId = columns[0].trim()  // CSV ID (e.g., "1", "2", etc.)
                            val name = columns[1].trim()
                            val description = columns[2].trim()
                            val parentId = columns[3].trim()

                            // Add the ID to UUID map
                            idToUuidMap[csvId] = id

                            // Resolve parent UUID if parentId is provided
                            val resolvedParentId = if (parentId.isNotBlank()) {
                                idToUuidMap[parentId]
                            } else {
                                null
                            }

                            val category = Category(id, name, description, resolvedParentId).apply { createdBy = "admin" }
                            categories.add(category)
                        } else {
                            logger.warn("Skipping invalid category line: $line")
                        }
                    }
            }

            categoryService.saveCategories(categories)
            logger.info("Successfully imported categories from ${file.filename}")
            return true
        } catch (ex: Exception) {
            logger.error("Error importing file: ${file.filename}", ex)
            return false
        }
    }

    private fun processProductFile(file: Resource): Boolean {
        logger.info("Importing products from file: ${file.filename}")
        val products = mutableListOf<ProductDto>()

        try {
            val reader = BufferedReader(InputStreamReader(file.inputStream))
            reader.useLines { lines ->
                lines.drop(1)  // Skip the header
                    .forEach { line ->
                        val columns = line.split(",")
                        if (columns.size >= 5) {
                            val id = UUID.randomUUID()
                            val name = columns[1].trim()
                            val description = columns[2].trim()
                            val price = columns[3].trim().toDouble()

                            // Retrieve category UUID based on CSV ID
                            val categoryId = columns[4].trim()
                            val categoryUUID = idToUuidMap[categoryId]

                            if (categoryUUID != null) {
                                val product = ProductDto(id, name, description, price, categoryUUID)
                                products.add(product)
                            } else {
                                logger.warn("Skipping product with invalid category ID: $categoryId")
                            }
                        } else {
                            logger.warn("Skipping invalid product line: $line")
                        }
                    }
            }

            productService.saveProducts(products)
            logger.info("Successfully imported products from ${file.filename}")
            return true
        } catch (ex: Exception) {
            logger.error("Error importing file: ${file.filename}", ex)
            return false
        }
    }

    data class ProductDto(val id: UUID, val name: String, val description: String, val price: Double, val categoryId: UUID)
}
