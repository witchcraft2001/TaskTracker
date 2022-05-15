package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.Colors
import com.dmdev.tasktracker.repositories.CategoriesRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend fun execute(id: Long, name: String, icon: CategoryIcon, color: Colors) {
        categoriesRepository.update(CategoryData(id, name, color.value.value, icon))
    }
}