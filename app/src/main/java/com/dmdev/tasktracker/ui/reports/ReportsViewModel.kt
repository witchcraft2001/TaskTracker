package com.dmdev.tasktracker.ui.reports

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModelEventHandler
import com.dmdev.tasktracker.data.domain.ReportPeriod
import com.dmdev.tasktracker.ui.reports.models.ReportsEvent
import com.dmdev.tasktracker.ui.reports.models.ReportsViewState
import com.dmdev.tasktracker.ui.utils.DateUtils
import com.dmdev.tasktracker.usecases.GetTasksReportInPeriodUseCase
import com.dmdev.tasktracker.usecases.ReportPeriodToDateRangeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val reportPeriodToDateRangeUseCase: ReportPeriodToDateRangeUseCase,
    private val reportInPeriodUseCase: GetTasksReportInPeriodUseCase,
    private val dateUtils: DateUtils
) : BaseViewModelEventHandler<ReportsViewState, ReportsEvent>(ReportsViewState.ShowReportViewState()) {
    override fun obtainEvent(event: ReportsEvent) {
        when (val state = uiState.value) {
            is ReportsViewState.ShowReportViewState -> reduce(event, state)
            is ReportsViewState.ErrorReportViewState -> reduce(event, state)
        }
    }

    private fun reduce(event: ReportsEvent, state: ReportsViewState.ShowReportViewState) {
        when (event) {
            is ReportsEvent.ReloadEvent -> loadReport()
            is ReportsEvent.SelectedPeriodEvent -> onPeriodChanged(event.period, state)
        }
    }

    private fun onPeriodChanged(period: ReportPeriod, state: ReportsViewState.ShowReportViewState) {
        val range = reportPeriodToDateRangeUseCase.execute(period)
        val startDate = dateUtils.dateToString(range.start)
        val endDate = dateUtils.dateToString(range.end)
        _uiState.value = state.copy(period = period, dateRange = range, dateRangeString = "$startDate - $endDate")
    }

    private fun reduce(event: ReportsEvent, state: ReportsViewState.ErrorReportViewState) {
        when (event) {
            is ReportsEvent.ReloadEvent -> loadReport()
        }
    }

    private fun loadReport() {
        viewModelScope.launch {

        }
    }
}