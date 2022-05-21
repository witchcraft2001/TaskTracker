package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.mappers.TaskMapper
import com.dmdev.tasktracker.repositories.CategoriesRepository
import com.dmdev.tasktracker.repositories.PeriodsRepository
import com.dmdev.tasktracker.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val categoriesRepository: CategoriesRepository,
    private val periodsRepository: PeriodsRepository,
) {
    suspend fun execute(): Flow<ResultWrapper<List<Task>>> {
        return flow {
            emit(ResultWrapper.Loading)
            try {
                combine(
                    tasksRepository.getAllTasks(),
                    categoriesRepository.getAllCategories(),
                    periodsRepository.getAllPeriodsFlow()
                )
                { tasks, categories, periods ->
                    when {
                        tasks is ResultWrapper.Success &&
                                categories is ResultWrapper.Success &&
                                periods is ResultWrapper.Success -> {
                            val result = tasks.result.map { item ->
                                TaskMapper.mapToDomain(
                                    item,
                                    categories.result.first { cat -> cat.id == item.categoryId },
                                    periods.result.filter { period -> period.taskId == item.id }
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
                    .collect()
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e))
            }
        }
    }
}