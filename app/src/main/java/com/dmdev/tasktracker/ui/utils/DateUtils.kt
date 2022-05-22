package com.dmdev.tasktracker.ui.utils

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtils @Inject constructor() {
    fun dateToString(date: Date): String =
        SimpleDateFormat.getDateInstance().format(date)
}
