package com.example.og.service

import com.example.og.entities.Category
import com.example.og.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    fun getCategoryById(categoryId: Long): Category? {
        return categoryRepository.findById(categoryId).orElse(null)
    }

    fun addCategory(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun updateCategory(categoryId: Long, updatedCategory: Category): Category? {
        val existingCategory = categoryRepository.findById(categoryId).orElse(null) ?: return null
        return categoryRepository.save(updatedCategory.copy(id = existingCategory.id))
    }

    fun deleteCategory(categoryId: Long) {
        categoryRepository.deleteById(categoryId)
    }
}
