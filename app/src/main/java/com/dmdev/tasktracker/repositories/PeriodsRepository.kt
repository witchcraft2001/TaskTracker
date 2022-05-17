package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.entities.PeriodEntity
import kotlinx.coroutines.flow.Flow

interface PeriodsRepository {
    suspend fun getAllPeriods(): Flow<ResultWrapper<List<PeriodEntity>>>
    suspend fun getAllUnfinishedPeriods(): List<PeriodEntity>
    suspend fun update(period: PeriodEntity)
    suspend fun add(period: PeriodEntity) : PeriodEntity
}