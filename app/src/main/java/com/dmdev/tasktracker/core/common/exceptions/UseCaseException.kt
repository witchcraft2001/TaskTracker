package com.dmdev.tasktracker.core.common.exceptions

class UseCaseException : Exception {
    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}
