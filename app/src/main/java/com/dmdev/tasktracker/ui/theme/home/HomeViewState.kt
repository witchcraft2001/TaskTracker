package com.dmdev.tasktracker.ui.theme.home

import com.dmdev.tasktracker.data.models.TaskModel

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val items: List<TaskModel>) : HomeViewState()
    data class Error(val exception: Throwable) : HomeViewState()
}