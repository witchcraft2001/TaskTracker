package com.dmdev.tasktracker.ui.utils.time_provider

import java.util.*

interface TimeProvider {
    fun getCurrentDate() : Date
}