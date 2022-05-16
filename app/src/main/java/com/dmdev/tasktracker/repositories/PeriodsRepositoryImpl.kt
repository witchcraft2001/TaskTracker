package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.dao.PeriodsDao
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
class PeriodsRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val periodsDao: PeriodsDao
) : PeriodsRepository {

    override suspend fun getAllPeriods(): Flow<ResultWrapper<List<PeriodData>>> {
        return flow {
            emit(ResultWrapper.Loading)
            val items = periodsDao.getAll()
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }

    override suspend fun getAllUnfinishedPeriods(): List<PeriodData> {
        return periodsDao.getAllUnfinished()
    }

    override suspend fun update(period: PeriodData) {
        periodsDao.update(period)
    }

    override suspend fun add(period: PeriodData): PeriodData {
        val id = periodsDao.add(period)
        return period.copy(id = id)
    }
}