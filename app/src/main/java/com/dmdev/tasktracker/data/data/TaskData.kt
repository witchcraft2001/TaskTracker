package com.dmdev.tasktracker.data.data

data class TaskData(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val startedAt: Long,
    val endedAt: Long?,
    val deadline: Long?
)
