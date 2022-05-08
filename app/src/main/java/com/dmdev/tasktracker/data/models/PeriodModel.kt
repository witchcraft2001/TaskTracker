package com.dmdev.tasktracker.data.models

data class PeriodModel(
    val id: Long,
    val taskId: Long,
    val startedAt: Long,
    val endedAt: Long?
)
