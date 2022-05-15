package com.dmdev.tasktracker.ui.home.models

sealed class TaskListViewState {
    data class ListViewState(
        val items: List<TaskModel> = emptyList(),
        val isLoading: Boolean = false
    ) : TaskListViewState()

    data class ErrorViewState(
        val message: String
    ) : TaskListViewState()
}
