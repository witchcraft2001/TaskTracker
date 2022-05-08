package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.mappers.TaskMapper
import com.dmdev.tasktracker.repositories.CategoriesRepository
import com.dmdev.tasktracker.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val categoriesRepository: CategoriesRepository
) {
    suspend fun getTasksWithCategories(): Flow<ResultWrapper<List<Task>>> {
        return flow {
            emit(ResultWrapper.Loading)
            try {
                tasksRepository.getAllTasks().combine(categoriesRepository.getAllCategories()) { tasks, categories ->
                    when {
                        tasks is ResultWrapper.Success && categories is ResultWrapper.Success -> {
                            val result = tasks.result.map { item ->
                                TaskMapper.mapToDomain(
                                    item,
                                    categories.result.first { cat -> cat.id == item.categoryId }
                                )
                            }
                            emit(ResultWrapper.Success(result))
                        }
                        tasks is ResultWrapper.Error -> emit(ResultWrapper.Error(tasks.exception))
                        categories is ResultWrapper.Error -> emit(ResultWrapper.Error(categories.exception))
                        tasks is ResultWrapper.Loading || categories is ResultWrapper.Loading ->
                            emit(ResultWrapper.Loading)
                    }
                }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e))
            }
        }
    }
}