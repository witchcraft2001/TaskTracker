package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.dao.PeriodsDao
import com.dmdev.tasktracker.data.entities.PeriodEntity
import com.dmdev.tasktracker.di.modules.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeriodsRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val periodsDao: PeriodsDao
) : PeriodsRepository {

    override suspend fun getAllPeriodsFlow(): Flow<ResultWrapper<List<PeriodEntity>>> {
        return flow {
            emit(ResultWrapper.Loading)
            val items = periodsDao.getAll()
            emit(ResultWrapper.Success(items))
        }.flowOn(dispatcher)
    }

    override suspend fun getAllPeriods(): List<PeriodEntity> {
        return periodsDao.getAll()
    }

    override suspend fun getAllUnfinishedPeriods(): List<PeriodEntity> {
        return periodsDao.getAllUnfinished()
    }

    override suspend fun update(period: PeriodEntity) {
        periodsDao.update(period)
    }

    override suspend fun add(period: PeriodEntity): PeriodEntity {
        val id = periodsDao.add(period)
        return period.copy(id = id)
    }
}