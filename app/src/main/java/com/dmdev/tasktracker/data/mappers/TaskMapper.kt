package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.data.entities.PeriodEntity
import com.dmdev.tasktracker.data.entities.TaskEntity

object TaskMapper {
    fun mapToDomain(data: TaskEntity, category: CategoryEntity, periods: List<PeriodEntity> = emptyList()) =
        Task(
            data.id,
            data.name,
            CategoryMapper.mapToDomain(category),
            data.startedAt,
            data.endedAt,
            data.deadline,
            periods.map { PeriodMapper.mapToDomain(it) })
}