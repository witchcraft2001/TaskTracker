package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun getAllTasks(): Flow<ResultWrapper<List<TaskEntity>>>
    suspend fun getAllUnfinishedTasks(): List<TaskEntity>
    suspend fun update(task: TaskEntity)
    suspend fun add(task: TaskEntity) : TaskEntity
    suspend fun get(id: Long): TaskEntity?
}