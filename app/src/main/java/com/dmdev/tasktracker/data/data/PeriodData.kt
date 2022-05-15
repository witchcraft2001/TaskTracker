package com.dmdev.tasktracker.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PeriodData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = PERIOD_TASK_ID_FIELD) val taskId: Long,
    @ColumnInfo(name = PERIOD_STARTED_AT_FIELD) val startedAt: Long,
    @ColumnInfo(name = PERIOD_ENDED_AT_FIELD) val endedAt: Long?
) {
    companion object {
        const val TABLE_NAME = "periods"
        const val PERIOD_TASK_ID_FIELD = "task_id"
        const val PERIOD_STARTED_AT_FIELD = "started_at"
        const val PERIOD_ENDED_AT_FIELD = "ended_at"
    }
}
