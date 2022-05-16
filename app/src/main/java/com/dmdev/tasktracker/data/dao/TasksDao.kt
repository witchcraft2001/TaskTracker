package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.data.data.TaskData

@Dao
interface TasksDao {
    @Insert
    suspend fun add(entity: TaskData) : Long

    @Update
    suspend fun update(entity: TaskData)

    @Query("SELECT * from ${TaskData.TABLE_NAME}")
    suspend fun getAll(): List<TaskData>

    @Query("SELECT * FROM ${TaskData.TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): TaskData?

    @Query("SELECT * FROM ${TaskData.TABLE_NAME} WHERE ${TaskData.PERIOD_ENDED_AT_FIELD} is null")
    fun getAllUnfinished(): List<TaskData>
}