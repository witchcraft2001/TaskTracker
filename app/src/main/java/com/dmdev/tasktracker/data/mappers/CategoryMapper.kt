package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.data.CategoryData

object CategoryMapper {
    fun mapToDomain(data: CategoryData) = Category(data.id, data.name, data.color, data.icon)
}
