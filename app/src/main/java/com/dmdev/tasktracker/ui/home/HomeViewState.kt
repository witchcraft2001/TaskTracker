package com.dmdev.tasktracker.ui.home

import com.dmdev.tasktracker.data.domain.Task

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val items: List<Task>) : HomeViewState()
    data class Error(val exception: Throwable) : HomeViewState()
}