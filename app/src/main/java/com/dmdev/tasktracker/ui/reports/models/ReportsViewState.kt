package com.dmdev.tasktracker.ui.reports.models

import com.dmdev.tasktracker.data.domain.DateRange
import com.dmdev.tasktracker.data.domain.ReportPeriod

sealed class ReportsViewState {
    data class ShowReportViewState(
        val isLoading: Boolean = false,
        val period: ReportPeriod = ReportPeriod.LAST_SEVEN_DAYS,
        val dateRange: DateRange? = null,
        val dateRangeString: String = "",
        val report: List<ReportModel> = emptyList(),
        val circleData: ReportCircleItemModel? = null
    ) : ReportsViewState()

    data class ErrorReportViewState(
        val message: String
    ) : ReportsViewState()
}