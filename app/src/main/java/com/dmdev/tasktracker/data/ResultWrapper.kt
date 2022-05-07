package com.dmdev.tasktracker.data

sealed class ResultWrapper<out T>{
    object Init: ResultWrapper<Nothing>()
    object Loading: ResultWrapper<Nothing>()
    class Success<out T>(val result: T): ResultWrapper<T>()
    class Error(val exception: Throwable): ResultWrapper<Nothing>()
}