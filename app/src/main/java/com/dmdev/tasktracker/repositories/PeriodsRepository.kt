package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.PeriodData
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
class PeriodsRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    private val items = mutableListOf(
        PeriodData(
            1,
            1,
            Date(2022-1900, 4, 5, 9, 0).time,
            Date(2022-1900, 4, 5, 9, 15).time,
        ),
        PeriodData(
            2,
            2,
            Date(2022-1900, 4, 5, 8, 0).time,
            Date(2022-1900, 4, 5, 8, 15).time,
        ),
        PeriodData(
            3,
            3,
            Date(2022-1900, 3, 10, 9, 0).time,
            Date(2022-1900, 3, 10, 10, 15).time,
        ),
        PeriodData(
            4,
            4,
            Date(2022-1900, 3, 7, 11, 30).time,
            Date(2022-1900, 3, 7, 15, 45).time,
        ),
        PeriodData(
            5,
            5,
            Date(2022-1900, 3, 15, 9, 0).time,
            Date(2022-1900, 3, 15, 10, 15).time,
        ),
        PeriodData(
            6,
            6,
            Date(2022-1900, 2, 20, 11, 30).time,
            Date(2022-1900, 2, 20, 15, 45).time,
        ),
        PeriodData(
            7,
            1,
            Date(2022-1900, 4, 6, 9, 0).time,
            Date(2022-1900, 4, 6, 9, 15).time,
        ),
    )

    suspend fun getAllPeriods(): Flow<ResultWrapper<List<PeriodData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            emit(
                ResultWrapper.Success(items)
            )
        }.flowOn(dispatcher)
    }

    suspend fun getAllUnfinishedPeriods() : List<PeriodData> {
        return items.filter { item -> item.endedAt == null }
    }

    suspend fun update(period: PeriodData) : PeriodData {
        val index = items.indexOfFirst { item -> item.id == period.id}
        if (index >= 0) {
            items.removeAt(index)
        }
        items.add(period)
        return period
    }

    suspend fun add(period: PeriodData) : PeriodData {
        val max = items.maxByOrNull { item -> item.id }
        val newPeriod = period.copy(id = max?.id ?: 1)
        items.add(newPeriod)
        return newPeriod
    }
}