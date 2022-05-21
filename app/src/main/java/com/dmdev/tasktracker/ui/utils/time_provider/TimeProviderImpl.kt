package com.dmdev.tasktracker.ui.utils.time_provider

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeProviderImpl @Inject constructor() : TimeProvider {
    override fun getCurrentDate(): Date = Date(System.currentTimeMillis())
}