package com.dmdev.tasktracker.core.extensions

fun String?.getOrElse(defaultValue: String) : String {
    return if (this.isNullOrEmpty()) {
        defaultValue
    } else {
        this
    }
}