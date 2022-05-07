package com.dmdev.tasktracker.data.domain

data class Task(
    val id: Long,
    val name: String,
    val category: Category,
    val startedAt: Long,
    val endedAt: Long?,
    val deadline: Long?
)
