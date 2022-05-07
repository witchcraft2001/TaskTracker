package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.models.TaskModel
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(
    @DefaultDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAllTasks(): Flow<ResultWrapper<List<TaskModel>>> {
        return flow {
            emit(ResultWrapper.Loading)

            emit(
                ResultWrapper.Success(
                    listOf(
                        TaskModel(
                            1,
                            "Разработать дизайн программы",
                            1,
                            Date(2022, 4, 5, 9, 0).time,
                            null,
                            null
                        ),
                        TaskModel(
                            2,
                            "Разработать структуру данных программы",
                            1,
                            Date(2022, 4, 5, 9, 0).time,
                            Date(2022, 4, 5, 9, 15).time,
                            null
                        )
                    )
                )
            )
        }.flowOn(ioDispatcher)
    }
}