package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    private val items = mutableListOf(
        CategoryData(1, "Shopping", 0xFFEF9A9A, CategoryIcon.SHOP),
        CategoryData(2, "Gaming", 0xFFF48FB1, CategoryIcon.GAME),
        CategoryData(3, "Studying", 0xFFCE93D8, CategoryIcon.STUDY),
        CategoryData(4, "Driving", 0xFFB39DDB, CategoryIcon.CAR),
        CategoryData(5, "Chilling", 0xFF9FA8DA, CategoryIcon.MUSIC),
        CategoryData(6, "Building", 0xFF90CAF9, CategoryIcon.BRUSH)
    )

    suspend fun getAllCategories() : Flow<ResultWrapper<List<CategoryData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }
}