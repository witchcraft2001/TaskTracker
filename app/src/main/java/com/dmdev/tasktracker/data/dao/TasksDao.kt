package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.entities.PeriodEntity
import com.dmdev.tasktracker.data.entities.TaskEntity

@Dao
interface TasksDao {
    @Insert
    suspend fun add(entity: TaskEntity) : Long

    @Update
    suspend fun update(entity: TaskEntity)

    @Query("SELECT t.${TaskEntity.TASK_ID_FIELD}, t.${TaskEntity.TASK_NAME_FIELD}, t.${TaskEntity.TASK_CATEGORY_ID_FIELD}, t.${TaskEntity.TASK_STARTED_AT_FIELD}, t.${TaskEntity.TASK_ENDED_AT_FIELD}, t.${TaskEntity.TASK_DEADLINE_FIELD}, MAX(p.${PeriodEntity.PERIOD_STARTED_AT_FIELD}) FROM ${TaskEntity.TABLE_NAME} as t " +
            "LEFT JOIN ${PeriodEntity.TABLE_NAME} as p on p.${PeriodEntity.PERIOD_TASK_ID_FIELD} = t.${TaskEntity.TASK_ID_FIELD} " +
            "GROUP BY t.${TaskEntity.TASK_ID_FIELD}, t.${TaskEntity.TASK_NAME_FIELD}, t.${TaskEntity.TASK_CATEGORY_ID_FIELD}, t.${TaskEntity.TASK_STARTED_AT_FIELD}, t.${TaskEntity.TASK_ENDED_AT_FIELD}, t.${TaskEntity.TASK_DEADLINE_FIELD} " +
            "ORDER BY p.${PeriodEntity.PERIOD_STARTED_AT_FIELD} DESC")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE ${TaskEntity.TASK_ID_FIELD} = :id")
    fun getById(id: Long): TaskEntity?

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE ${TaskEntity.TASK_ID_FIELD} IN (:idList)")
    fun getByIdList(idList: List<Long>): List<TaskEntity>

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE ${TaskEntity.TASK_ENDED_AT_FIELD} is null")
    fun getAllUnfinished(): List<TaskEntity>
}