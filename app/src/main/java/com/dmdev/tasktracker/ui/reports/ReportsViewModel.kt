package com.dmdev.tasktracker.ui.reports

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModelEventHandler
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.ReportPeriod
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.ui.reports.models.ReportCircleItemModel
import com.dmdev.tasktracker.ui.reports.models.ReportModel
import com.dmdev.tasktracker.ui.reports.models.ReportsEvent
import com.dmdev.tasktracker.ui.reports.models.ReportsViewState
import com.dmdev.tasktracker.ui.utils.DateUtils
import com.dmdev.tasktracker.usecases.GetTasksReportInPeriodUseCase
import com.dmdev.tasktracker.usecases.ReportPeriodToDateRangeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
        loadReport()
    }

    private fun reduce(event: ReportsEvent, state: ReportsViewState.ErrorReportViewState) {
        when (event) {
            is ReportsEvent.ReloadEvent -> loadReport()
        }
    }

    private fun loadReport() {
        uiState.value.also { state ->
            if (state is ReportsViewState.ShowReportViewState && state.dateRange != null) {
                viewModelScope.launch {
                    reportInPeriodUseCase.execute(state.dateRange).collect { result ->
                        when (result) {
                            is ResultWrapper.Loading -> _uiState.value = state.copy(isLoading = true, circleData = null)
                            is ResultWrapper.Success -> prepareReportState(state, result.result)
                            is ResultWrapper.Error -> _uiState.value =
                                ReportsViewState.ErrorReportViewState(result.exception.message ?: "")
                        }
                    }
                }
            }
        }
    }

    private fun prepareReportState(state: ReportsViewState.ShowReportViewState, tasks: List<Task>) {
        val reportData = mapTasksToReportModels(tasks)
        val totalSpentTime = reportData.sumOf { item -> item.spentTime }.run {
            if (this > 0) {
                this
            } else {
                1
            }
        }
        val proportions = reportData.map { item -> item.spentTime.toFloat() / totalSpentTime }

        _uiState.value = state.copy(
            report = reportData,
            isLoading = false,
            circleData = ReportCircleItemModel(reportData.map { item -> Color(item.color.value.value) }, proportions)
        )
    }

    private fun mapTasksToReportModels(tasks: List<Task>): List<ReportModel> {
        val categories = tasks.map { item -> item.category }.distinct()
        val reportItems = categories.map { item ->
            val spentTime = tasks.filter { task -> task.category.id == item.id }.sumOf { task ->
                task.periods.sumOf { period ->
                    (period.endedAt ?: System.currentTimeMillis()) - period.startedAt
                }
            }
            ReportModel(categoryName = item.name, color = item.color, spentTime = spentTime)
        }
        return reportItems
    }


}