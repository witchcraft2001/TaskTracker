package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.Colors
import com.dmdev.tasktracker.data.data.parseColor

object CategoryMapper {
    fun mapToDomain(data: CategoryData) = Category(data.id, data.name, data.color.parseColor(), data.icon)
    fun mapToData(domain: Category) = CategoryData(domain.id, domain.name, domain.color.value.value, domain.icon)
}
