package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.models.CategoryModel

object CategoryMapper {
    fun mapToDomain(model: CategoryModel) = Category(model.id, model.name, model.color, model.icon)
}
