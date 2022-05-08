package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeriodsRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllPeriods(): Flow<ResultWrapper<List<PeriodData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            emit(
                ResultWrapper.Success(
                    listOf(
                        PeriodData(
                            1,
                            1,
                            Date(2022, 4, 5, 9, 0).time,
                            Date(2022, 4, 5, 9, 15).time,
                        ),
                        PeriodData(
                            2,
                            2,
                            Date(2022, 4, 5, 8, 0).time,
                            Date(2022, 4, 5, 8, 15).time,
                        ),
                        PeriodData(
                            3,
                            3,
                            Date(2022, 3, 10, 9, 0).time,
                            Date(2022, 3, 10, 10, 15).time,
                        ),
                        PeriodData(
                            4,
                            4,
                            Date(2022, 3, 7, 11, 30).time,
                            Date(2022, 3, 7, 15, 45).time,
                        ),
                        PeriodData(
                            5,
                            5,
                            Date(2022, 3, 15, 9, 0).time,
                            Date(2022, 3, 15, 10, 15).time,
                        ),
                        PeriodData(
                            6,
                            6,
                            Date(2022, 2, 20, 11, 30).time,
                            Date(2022, 2, 20, 15, 45).time,
                        ),
                        PeriodData(
                            7,
                            1,
                            Date(2022, 4, 6, 9, 0).time,
                            Date(2022, 4, 6, 9, 15).time,
                        ),
                    )
                )
            )
        }.flowOn(dispatcher)
    }
}