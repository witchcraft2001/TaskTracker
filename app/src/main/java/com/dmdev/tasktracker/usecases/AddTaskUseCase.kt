package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.data.data.TaskData
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.mappers.CategoryMapper
import com.dmdev.tasktracker.data.mappers.TaskMapper
import com.dmdev.tasktracker.repositories.PeriodsRepository
import com.dmdev.tasktracker.repositories.TasksRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val periodsRepository: PeriodsRepository,
) {
    suspend fun execute(name: String, category: Category): Flow<ResultWrapper<Task>> {
        return flow {
            emit(ResultWrapper.Loading)
            try {
                val endTime = System.currentTimeMillis()
                val periods = periodsRepository.getAllUnfinishedPeriods()
                periods.forEach { item -> periodsRepository.update(item.copy(endedAt = endTime)) }
                val tasks = tasksRepository.getAllUnfinishedTasks()
                tasks.forEach { item -> tasksRepository.update(item.copy(endedAt = endTime)) }

                val startTime = System.currentTimeMillis()
                val task = TaskData(
                    id = 0,
                    name = name,
                    categoryId = category.id,
                    startedAt = startTime,
                    null,
                    null
                )
                val newTask = tasksRepository.add(task)
                val period = PeriodData(id = 0, taskId = newTask.id, startedAt = startTime, endedAt = null)
                val newPeriod = periodsRepository.add(period)
                delay(500)
                emit(
                    ResultWrapper.Success(
                        TaskMapper.mapToDomain(
                            newTask,
                            CategoryMapper.mapToData(category),
                            listOf(newPeriod)
                        )
                    )
                )
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e))
            }
        }
    }
}