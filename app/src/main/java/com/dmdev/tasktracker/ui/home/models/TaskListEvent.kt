package com.dmdev.tasktracker.ui.home.models

sealed class TaskListEvent {
    object ReloadEvent: TaskListEvent()
    data class ToggleTaskEvent(val task: TaskModel): TaskListEvent()
}
