package com.example.og.configs

import com.example.og.entities.Category
import com.example.og.service.CategoryService
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
class CategoryInitializer(
    private val categoryService: CategoryService,
    private val fileResourceLoader: FileResourceLoader,
    ) {
    val logger = LoggerFactory.getLogger(CategoryInitializer::class.java)!!

    @EventListener(ApplicationReadyEvent::class)
    @Order(10)
    fun onStartup() {
        logger.info("Clearing lookup repository")

        val files = fileResourceLoader.loadFiles("test-data/category.csv")
        files.map { file -> processFile(file) }

    }

    private fun processFile(file: Resource): Boolean {
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
                            val name = columns[1].trim()
                            val description = columns[2].trim()
                            val parentId = if (columns[3].isNotBlank()) UUID.randomUUID() else null

                            val category = Category(id, name, description, parentId)
                            categories.add(category)
                        } else {
                            logger.warn("Skipping invalid line: $line")
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


    data class Category(
        val id: UUID,
        val name: String,
        val description: String,
        val parentId: UUID? = null
    )
}

