package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.data.parseColor

object CategoryMapper {
    fun mapToDomain(data: CategoryEntity) = Category(data.id, data.name, data.color.parseColor(), data.icon)
    fun mapToData(domain: Category) = CategoryEntity(domain.id, domain.name, domain.color.value.value.toLong(), domain.icon)
}
