package com.dmdev.tasktracker.core.common

interface EventHandler<T> {
    fun obtainEvent(event: T)
}