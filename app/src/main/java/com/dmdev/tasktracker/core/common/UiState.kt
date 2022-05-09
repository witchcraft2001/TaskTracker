package com.dmdev.tasktracker.core.common

sealed class UiState<T> {
    data class Success<T>(val value: T) : UiState<T>()

    class Empty<T> : UiState<T>()

    class Loading<T> : UiState<T>()

    class Finished<T> : UiState<T>()

    class NetworkError<T> : UiState<T>()

    data class Error<T>(val message: String) : UiState<T>()

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
