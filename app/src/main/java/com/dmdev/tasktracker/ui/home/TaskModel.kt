package com.dmdev.tasktracker.ui.home

import com.dmdev.tasktracker.data.domain.Category

data class TaskModel(
    val id: Long,
    val name: String,
    val category: Category,
    val startedAt: Long,
    val endedAt: Long?,
    val deadline: Long?,
    val elapsedTime: String
)