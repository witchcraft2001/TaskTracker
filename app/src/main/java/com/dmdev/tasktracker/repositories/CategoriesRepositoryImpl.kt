package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.dao.CategoriesDao
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val categoriesDao: CategoriesDao
) : CategoriesRepository {

    override suspend fun getAllCategories() : Flow<ResultWrapper<List<CategoryEntity>>> {
        return flow {
            emit(ResultWrapper.Loading)
            val items = categoriesDao.getAll()
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }

    override suspend fun getCategoriesByIds(ids: List<Long>): List<CategoryEntity> {
        return categoriesDao.getByIdList(ids)
    }

    override suspend fun add(category: CategoryEntity) {
        categoriesDao.add(category)
    }

    override suspend fun update(category: CategoryEntity) {
        categoriesDao.update(category)
    }
}