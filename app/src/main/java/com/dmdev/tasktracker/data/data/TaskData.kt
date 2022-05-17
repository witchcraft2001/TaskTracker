package com.dmdev.tasktracker.data.data

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = TaskData.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = TaskData::class,
            parentColumns = arrayOf(CategoryData.CATEGORY_ID_FIELD),
            childColumns = arrayOf(TaskData.TASK_CATEGORY_ID_FIELD),
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(TaskData.TASK_CATEGORY_ID_FIELD)
    ]
)
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TASK_ID_FIELD)
    val id: Long,
    @ColumnInfo(name = TASK_NAME_FIELD)
    val name: String,
    @ColumnInfo(name = TASK_CATEGORY_ID_FIELD)
    val categoryId: Long,
    @ColumnInfo(name = TASK_STARTED_AT_FIELD)
    val startedAt: Long,
    @ColumnInfo(name = TASK_ENDED_AT_FIELD)
    val endedAt: Long?,
    @ColumnInfo(name = TASK_DEADLINE_FIELD)
    val deadline: Long?
) {
    companion object {
        const val TABLE_NAME = "tasks"
        const val TASK_ID_FIELD = "id"
        const val TASK_NAME_FIELD = "name"
        const val TASK_CATEGORY_ID_FIELD = "category_id"
        const val TASK_STARTED_AT_FIELD = "started_at"
        const val TASK_ENDED_AT_FIELD = "ended_at"
        const val TASK_DEADLINE_FIELD = "deadline"
    }

    constructor() : this(0L, "", 0L, 0L, null, null)
}
