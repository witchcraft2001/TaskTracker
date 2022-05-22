package com.dmdev.tasktracker.ui.reports.models

import com.dmdev.tasktracker.data.domain.ReportPeriod

sealed class ReportsEvent {
    data class SelectedPeriodEvent(val period: ReportPeriod) : ReportsEvent()
    object ReloadEvent : ReportsEvent()
}