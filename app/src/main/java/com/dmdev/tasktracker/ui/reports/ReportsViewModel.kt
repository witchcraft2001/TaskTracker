package com.dmdev.tasktracker.ui.reports

import com.dmdev.tasktracker.core.common.BaseViewModelEventHandler
import com.dmdev.tasktracker.ui.reports.models.ReportsEvent
import com.dmdev.tasktracker.ui.reports.models.ReportsViewState

class ReportsViewModel :
    BaseViewModelEventHandler<ReportsViewState, ReportsEvent>(ReportsViewState.ShowReportViewState()) {
    override fun obtainEvent(event: ReportsEvent) {
        when (val state = uiState.value) {
            is ReportsViewState.ShowReportViewState -> reduce(event, state)
        }
    }

    private fun reduce(event: ReportsEvent, state: ReportsViewState.ShowReportViewState) {
        TODO("Not yet implemented")
    }
}