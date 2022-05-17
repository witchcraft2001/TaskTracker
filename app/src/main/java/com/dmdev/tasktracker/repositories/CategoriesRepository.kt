package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories() : Flow<ResultWrapper<List<CategoryEntity>>>
    suspend fun add(category: CategoryEntity)
    suspend fun update(category: CategoryEntity)
}
