package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.TaskData
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
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllTasks(): Flow<ResultWrapper<List<TaskData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            emit(
                ResultWrapper.Success(
                    listOf(
                        TaskData(
                            1,
                            "Разработать дизайн программы",
                            3,
                            Date(2022-1900, 4, 5, 9, 0).time,
                            null,
                            null
                        ),
                        TaskData(
                            2,
                            "Разработать структуру данных программы",
                            3,
                            Date(2022-1900, 4, 5, 8, 0).time,
                            Date(2022-1900, 4, 5, 8, 15).time,
                            null
                        ),
                        TaskData(
                            3,
                            "Поклеить обои",
                            6,
                            Date(2022-1900, 3, 10, 9, 0).time,
                            Date(2022-1900, 3, 10, 10, 15).time,
                            null
                        ),
                        TaskData(
                            4,
                            "Сходить на концерт",
                            5,
                            Date(2022-1900, 3, 7, 11, 30).time,
                            Date(2022-1900, 3, 7, 15, 45).time,
                            null
                        ),
                        TaskData(
                            5,
                            "Помыть машину",
                            4,
                            Date(2022-1900, 3, 15, 9, 0).time,
                            Date(2022-1900, 3, 15, 10, 15).time,
                            null
                        ),
                        TaskData(
                            6,
                            "Поиграть в настолки",
                            2,
                            Date(2022-1900, 2, 20, 11, 30).time,
                            Date(2022-1900, 2, 20, 15, 45).time,
                            null
                        )
                    )
                )
            )
        }.flowOn(dispatcher)
    }
}