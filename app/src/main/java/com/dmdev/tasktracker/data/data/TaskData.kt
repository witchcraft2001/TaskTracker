package com.dmdev.tasktracker.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TaskData.TABLE_NAME)
data class TaskData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = PERIOD_NAME_FIELD) val name: String,
    @ColumnInfo(name = PERIOD_CATEGORY_ID_FIELD) val categoryId: Long,
    @ColumnInfo(name = PERIOD_STARTED_AT_FIELD) val startedAt: Long,
    @ColumnInfo(name = PERIOD_ENDED_AT_FIELD) val endedAt: Long?,
    @ColumnInfo(name = PERIOD_DEADLINE_FIELD) val deadline: Long?
) {
    companion object {
        const val TABLE_NAME = "tasks"
        const val PERIOD_NAME_FIELD = "name"
        const val PERIOD_CATEGORY_ID_FIELD = "categoryId"
        const val PERIOD_STARTED_AT_FIELD = "started_at"
        const val PERIOD_ENDED_AT_FIELD = "ended_at"
        const val PERIOD_DEADLINE_FIELD = "deadline"
    }

    constructor() : this(0L, "", 0L, 0L, null, null)
}
