package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.Colors
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : CategoriesRepository {
    private val items = mutableListOf(
        CategoryData(1, "Shopping", Colors.BLUE.value.value, CategoryIcon.SHOP),
        CategoryData(2, "Gaming", Colors.CIAN.value.value, CategoryIcon.GAME),
        CategoryData(3, "Studying", Colors.DEEP_PURPLE.value.value, CategoryIcon.STUDY),
        CategoryData(4, "Driving", Colors.GREEN.value.value, CategoryIcon.CAR),
        CategoryData(5, "Chilling", Colors.INDIGO.value.value, CategoryIcon.MUSIC),
        CategoryData(6, "Building", Colors.ORANGE.value.value, CategoryIcon.BRUSH)
    )

    override suspend fun getAllCategories() : Flow<ResultWrapper<List<CategoryData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }

    override suspend fun add(category: CategoryData): CategoryData {
        val max = items.maxByOrNull { item -> item.id }
        val newCategory = category.copy(id = (max?.id ?: 0) + 1)
        items.add(newCategory)
        return newCategory
    }

    override suspend fun update(category: CategoryData): CategoryData {
        val index = items.indexOfFirst { item -> item.id == category.id}
        if (index >= 0) {
            items.removeAt(index)
        }
        items.add(category)
        return category
    }
}