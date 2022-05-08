package com.dmdev.tasktracker.data.data

data class PeriodData(
    val id: Long,
    val taskId: Long,
    val startedAt: Long,
    val endedAt: Long?
)
