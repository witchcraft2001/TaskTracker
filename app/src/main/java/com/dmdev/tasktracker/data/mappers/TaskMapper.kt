package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.data.models.CategoryModel
import com.dmdev.tasktracker.data.models.TaskModel

object TaskMapper {
    fun mapToDomain(model: TaskModel, category: CategoryModel) =
        Task(model.id, model.name, CategoryMapper.mapToDomain(category), model.startedAt, model.endedAt, model.deadline)
}