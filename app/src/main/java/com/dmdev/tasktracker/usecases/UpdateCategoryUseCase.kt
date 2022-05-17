package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.CategoryIcon
import com.dmdev.tasktracker.data.Colors
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.repositories.CategoriesRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend fun execute(id: Long, name: String, icon: CategoryIcon, color: Colors) {
        categoriesRepository.update(CategoryEntity(id, name, color.value.value.toLong(), icon))
    }
}