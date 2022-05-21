package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.DateRange
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.mappers.TaskMapper
import com.dmdev.tasktracker.repositories.CategoriesRepository
import com.dmdev.tasktracker.repositories.PeriodsRepository
import com.dmdev.tasktracker.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTasksReportInPeriodUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val periodsRepository: PeriodsRepository,
    private val categoriesRepository: CategoriesRepository,
) {
    suspend fun execute(period: DateRange): Flow<ResultWrapper<List<Task>>> {
        return flow {
            emit(ResultWrapper.Loading)
            try {
                val allPeriods = periodsRepository.getAllPeriods()
                val filtered = allPeriods.filter { item ->
                    period.start.time <= item.startedAt && period.end.time >= item.startedAt ||
                            period.start.time >= item.startedAt && item.endedAt == null || period.end.time <= item.startedAt ||
                            item.endedAt != null && period.start.time <= item.endedAt && period.end.time >= item.endedAt
                }.map { item ->
                    item.copy(
                        startedAt = if (item.startedAt < period.start.time) period.start.time else item.startedAt,
                        endedAt = item.endedAt ?: period.end.time
                    )
                }
                val tasks = tasksRepository.getTasksByIds(filtered.map { item -> item.id }.distinct())
                val categories =
                    categoriesRepository.getCategoriesByIds(tasks.map { item -> item.categoryId }.distinct())
                emit(ResultWrapper.Success(tasks.map { item ->
                    TaskMapper.mapToDomain(
                        item,
                        categories.first { i -> i.id == item.categoryId },
                        filtered.filter { i -> i.taskId == item.id })
                }))
            } catch (e: Throwable) {
                emit(ResultWrapper.Error(e))
            }
        }
    }
}