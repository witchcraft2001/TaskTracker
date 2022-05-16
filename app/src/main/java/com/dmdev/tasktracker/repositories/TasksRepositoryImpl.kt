package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.dao.TasksDao
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.data.data.TaskData
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val tasksDao: TasksDao
) : TasksRepository {

    override suspend fun getAllTasks(): Flow<ResultWrapper<List<TaskData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            val items = tasksDao.getAll()
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }

    override suspend fun getAllUnfinishedTasks(): List<TaskData> {
        return tasksDao.getAllUnfinished()
    }

    override suspend fun update(task: TaskData) {
        tasksDao.update(task)
    }

    override suspend fun add(task: TaskData): TaskData {
        val id = tasksDao.add(task)
        return task.copy(id = id)
    }

    override suspend fun get(id: Long): TaskData? {
        return tasksDao.getById(id)
    }
}