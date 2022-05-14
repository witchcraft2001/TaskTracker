package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.core.common.exceptions.UseCaseException
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.mappers.CategoryMapper
import com.dmdev.tasktracker.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
) {
    suspend fun execute(): Flow<ResultWrapper<List<Category>>> {
        return flow {
            emit(ResultWrapper.Loading)
            try {
                categoriesRepository.getAllCategories()
                    .collect { result ->
                        when (result) {
                            is ResultWrapper.Success -> emit(ResultWrapper.Success(result.result.map {
                                CategoryMapper.mapToDomain(it)
                            }))
                            is ResultWrapper.Loading -> emit(result)
                            is ResultWrapper.Error -> emit(result)
                            else -> emit(ResultWrapper.Error(UseCaseException("Unknown result $result")))
                        }
                    }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e))
            }
        }
    }
}