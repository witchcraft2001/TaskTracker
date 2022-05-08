package com.dmdev.tasktracker.data.domain

data class Period(
    val id: Long,
    val taskId: Long,
    val startedAt: Long,
    val endedAt: Long?
)
