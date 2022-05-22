package com.dmdev.tasktracker.data.domain

import com.dmdev.tasktracker.R

enum class ReportPeriod(val id: Int) {
    LAST_SEVEN_DAYS(R.string.text_last_seven_days),
    LAST_THIRTY_DAYS(R.string.text_last_thirty_days),
    MONTH(R.string.text_last_month),
    QUARTER(R.string.text_last_quarter),
    YEAR(R.string.text_last_year)
}