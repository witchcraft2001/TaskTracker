package com.dmdev.tasktracker.utils

import android.content.Context
import com.dmdev.tasktracker.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TimeUtils @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        private const val TIME_PART_FORMAT = "%02d:"
        private const val TIME_PART_ENDS_FORMAT = "%02d"
    }

    private val daysCountStringFormat: String = context.getString(R.string.text_days_count_format)

    fun millisToTimeString(time: Long, withSeconds: Boolean = true): String {
        val sb = StringBuilder()
        val days = TimeUnit.MILLISECONDS.toDays(time)
        if (days > 0) {
            sb.append(String.format(daysCountStringFormat, days))
        }
        var temp = time - TimeUnit.DAYS.toMillis(days)
        val hours = TimeUnit.MILLISECONDS.toHours(temp)
        if (hours > 0) {
            sb.append(String.format(TIME_PART_FORMAT, hours))
        }
        temp -= TimeUnit.HOURS.toMillis(hours)

        val minutes = TimeUnit.MILLISECONDS.toMinutes(temp)
        temp -= TimeUnit.MINUTES.toMillis(minutes)
        if (withSeconds) {
            sb.append(String.format(TIME_PART_FORMAT, minutes))
        } else {
            sb.append(String.format(TIME_PART_ENDS_FORMAT, minutes))
        }

        if (withSeconds) {
            val seconds = TimeUnit.MILLISECONDS.toSeconds(temp)
            sb.append(String.format(TIME_PART_ENDS_FORMAT, seconds))
        }

        return sb.toString()
    }
}