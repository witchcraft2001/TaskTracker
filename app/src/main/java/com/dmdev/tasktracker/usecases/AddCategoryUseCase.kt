package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.Colors
import com.dmdev.tasktracker.repositories.CategoriesRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend fun execute(name: String, icon: CategoryIcon, color: Colors) {
        categoriesRepository.add(CategoryData(0, name, color.value.value, icon))
    }
}