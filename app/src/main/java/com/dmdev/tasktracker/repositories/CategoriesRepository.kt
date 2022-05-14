package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.CategoryData
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories() : Flow<ResultWrapper<List<CategoryData>>>
}
