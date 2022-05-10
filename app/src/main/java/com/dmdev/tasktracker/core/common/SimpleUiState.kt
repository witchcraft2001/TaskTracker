package com.dmdev.tasktracker.core.common

sealed class SimpleUiState {
    object Success : SimpleUiState()

    object Empty : SimpleUiState()

    object Loading : SimpleUiState()

    object Finished : SimpleUiState()

    object NetworkError : SimpleUiState()

    data class Error(val message: String) : SimpleUiState()

    val isSuccess
        get() = this is Success
    val isEmpty
        get() = this is Empty
    val isLoading
        get() = this is Loading
    val isError
        get() = this is Error
    val isNetworkError
        get() = this is NetworkError
    val isFinished
        get() = this is Finished
}
