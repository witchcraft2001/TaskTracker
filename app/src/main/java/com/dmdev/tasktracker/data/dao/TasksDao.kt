package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.entities.TaskEntity

@Dao
interface TasksDao {
    @Insert
    suspend fun add(entity: TaskEntity) : Long

    @Update
    suspend fun update(entity: TaskEntity)

    @Query("SELECT * from ${TaskEntity.TABLE_NAME}")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): TaskEntity?

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE ${TaskEntity.TASK_ENDED_AT_FIELD} is null")
    fun getAllUnfinished(): List<TaskEntity>
}