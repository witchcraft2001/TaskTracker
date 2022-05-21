package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.domain.DateRange
import com.dmdev.tasktracker.data.domain.ReportPeriod
import com.dmdev.tasktracker.ui.utils.time_provider.TimeProvider
import java.util.*
import javax.inject.Inject

class ReportPeriodToDateRangeUseCase @Inject constructor(
    private val timeProvider: TimeProvider
) {
    fun execute(period: ReportPeriod): DateRange {
        return when (period) {
            ReportPeriod.LAST_SEVEN_DAYS -> getLastSevenDaysPeriod()
            ReportPeriod.LAST_THIRTY_DAYS -> getLastThirtyDaysPeriod()
            ReportPeriod.MONTH -> getLastMonthPeriod()
            ReportPeriod.QUARTER -> getLastQuarterPeriod()
            ReportPeriod.YEAR -> getLastYearPeriod()
        }
    }

    private fun getLastThirtyDaysPeriod(): DateRange {
        val end = Calendar.getInstance().apply {
            time = timeProvider.getCurrentDate()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.MILLISECOND, -1)
        }.time
        val start = Calendar.getInstance().apply {
            time = end
            add(Calendar.MILLISECOND, 1)
            add(Calendar.DAY_OF_MONTH, -30)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return DateRange(start = start.time, end = end)
    }

    private fun getLastYearPeriod(): DateRange {
        val end = Calendar.getInstance().apply {
            time = timeProvider.getCurrentDate()
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.MONTH, 0)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.MILLISECOND, -1)
        }
        val start = (end.clone() as Calendar).apply {
            add(Calendar.MILLISECOND, 1)
            add(Calendar.YEAR, -1)
        }
        return DateRange(start = start.time, end = end.time)
    }

    private fun getLastQuarterPeriod(): DateRange {
        val calendar = Calendar.getInstance().apply {
            time = timeProvider.getCurrentDate()
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val endMonth = when (calendar.get(Calendar.MONTH)) {
            in 0..2 -> 0
            in 3..5 -> 3
            in 6..8 -> 6
            else -> 9
        }
        val end = (calendar.clone() as Calendar).apply {
            set(Calendar.MONTH, endMonth)
            add(Calendar.MILLISECOND, -1)
        }
        val start = (end.clone() as Calendar).apply {
            add(Calendar.MILLISECOND, 1)
            add(Calendar.MONTH, -3)
        }
        return DateRange(start = start.time, end = end.time)
    }

    private fun getLastMonthPeriod(): DateRange {
        val end = Calendar.getInstance().apply {
            time = timeProvider.getCurrentDate()
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.MILLISECOND, -1)
        }
        val start = (end.clone() as Calendar).apply {
            add(Calendar.MILLISECOND, 1)
            add(Calendar.MONTH, -1)
        }
        return DateRange(start = start.time, end = end.time)
    }

    private fun getLastSevenDaysPeriod(): DateRange {
        val end = Calendar.getInstance().apply {
            time = timeProvider.getCurrentDate()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.MILLISECOND, -1)
        }
        val start = Calendar.getInstance().apply {
            time = timeProvider.getCurrentDate()
            add(Calendar.DAY_OF_MONTH, -7)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return DateRange(start = start.time, end = end.time)
    }
}