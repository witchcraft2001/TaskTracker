package com.dmdev.tasktracker.ui.reports.models

sealed class ReportsViewState {
    data class ShowReportViewState(
        val isLoading: Boolean = false
    ) : ReportsViewState()

    data class ErrorReportViewState(
        val message: String
    ) : ReportsViewState()
}