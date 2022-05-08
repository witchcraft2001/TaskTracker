package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.data.data.TaskData

object TaskMapper {
    fun mapToDomain(data: TaskData, category: CategoryData, periods: List<PeriodData> = emptyList()) =
        Task(
            data.id,
            data.name,
            CategoryMapper.mapToDomain(category),
            data.startedAt,
            data.endedAt,
            data.deadline,
            periods.map { PeriodMapper.mapToDomain(it) })
}