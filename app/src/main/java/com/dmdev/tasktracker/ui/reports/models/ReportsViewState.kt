package com.dmdev.tasktracker.ui.reports.models

import com.dmdev.tasktracker.data.domain.ReportPeriod

sealed class ReportsViewState {
    data class ShowReportViewState(
        val isLoading: Boolean = false,
        val period: ReportPeriod = ReportPeriod.LAST_SEVEN_DAYS
    ) : ReportsViewState()

    data class ErrorReportViewState(
        val message: String
    ) : ReportsViewState()
}