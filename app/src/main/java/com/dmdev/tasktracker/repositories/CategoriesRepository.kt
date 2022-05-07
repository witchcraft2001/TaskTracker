package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.models.CategoryModel
import com.dmdev.tasktracker.data.models.CategoryIcon
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    @DefaultDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAllCategories() : Flow<ResultWrapper<List<CategoryModel>>> {
        return flow {
            emit(ResultWrapper.Loading)
            emit(ResultWrapper.Success(listOf(
                CategoryModel(1, "Shopping", 0xFF808080, CategoryIcon.SHOP),
                CategoryModel(2, "Gaming", 0xFF909090, CategoryIcon.GAME),
                CategoryModel(3, "Studying", 0xFF606060, CategoryIcon.STUDY),
                CategoryModel(4, "Driving", 0xFF505050, CategoryIcon.CAR),
                CategoryModel(5, "Chilling", 0xFF707070, CategoryIcon.MUSIC),
                CategoryModel(6, "Building", 0xFFA0A0A0, CategoryIcon.BRUSH)
            )))
        }.flowOn(ioDispatcher)
    }
}