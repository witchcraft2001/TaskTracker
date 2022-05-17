package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.dao.TasksDao
import com.dmdev.tasktracker.data.entities.TaskEntity
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val tasksDao: TasksDao
) : TasksRepository {

    override suspend fun getAllTasks(): Flow<ResultWrapper<List<TaskEntity>>> {
        return flow {
            emit(ResultWrapper.Loading)
            val items = tasksDao.getAll()
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }

    override suspend fun getAllUnfinishedTasks(): List<TaskEntity> {
        return tasksDao.getAllUnfinished()
    }

    override suspend fun update(task: TaskEntity) {
        tasksDao.update(task)
    }

    override suspend fun add(task: TaskEntity): TaskEntity {
        val id = tasksDao.add(task)
        return task.copy(id = id)
    }

    override suspend fun get(id: Long): TaskEntity? {
        return tasksDao.getById(id)
    }
}