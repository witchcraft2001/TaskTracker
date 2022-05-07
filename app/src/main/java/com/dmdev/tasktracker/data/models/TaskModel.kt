package com.dmdev.tasktracker.data.models

data class TaskModel(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val startedAt: Long,
    val endedAt: Long?,
    val deadline: Long?
)
