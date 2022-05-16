package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.TaskData
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun getAllTasks(): Flow<ResultWrapper<List<TaskData>>>
    suspend fun getAllUnfinishedTasks(): List<TaskData>
    suspend fun update(task: TaskData)
    suspend fun add(task: TaskData) : TaskData
    suspend fun get(id: Long): TaskData?
}