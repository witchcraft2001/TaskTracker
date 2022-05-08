package com.dmdev.tasktracker.data.domain

import com.dmdev.tasktracker.data.data.CategoryIcon

data class Category(
    val id: Long,
    val name: String,
    val color: Long,
    val icon: CategoryIcon
)
