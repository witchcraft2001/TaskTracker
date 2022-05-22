package com.dmdev.tasktracker.ui.reports.models

import com.dmdev.tasktracker.data.Colors

data class ReportModel(
    val categoryName: String,
    val color: Colors,
    val spentTime: Long
)