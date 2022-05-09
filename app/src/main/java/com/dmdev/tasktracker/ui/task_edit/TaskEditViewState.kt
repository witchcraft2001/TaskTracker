package com.dmdev.tasktracker.ui.task_edit

import com.dmdev.tasktracker.data.domain.Category

data class TaskEditViewState(
    val id: Long? = null,
    val name: String = "",
    val nameError: Boolean = false,
    val category: Category? = null,
    val categoryError: Boolean = false
)
