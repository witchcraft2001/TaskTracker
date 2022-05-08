package com.dmdev.tasktracker.ui.home

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val items: List<TaskModel>) : HomeViewState()
    data class Error(val exception: Throwable) : HomeViewState()
}