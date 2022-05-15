package com.dmdev.tasktracker.core.common

abstract class BaseViewModelEventHandler<T, E>(initialViewState: T) : BaseViewModel<T>(initialViewState) {
    abstract fun obtainEvent(event: E)
}