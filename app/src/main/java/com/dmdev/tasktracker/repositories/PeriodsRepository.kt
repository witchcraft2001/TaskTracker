package com.dmdev.tasktracker.repositories

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.PeriodData
import kotlinx.coroutines.flow.Flow

interface PeriodsRepository {
    suspend fun getAllPeriods(): Flow<ResultWrapper<List<PeriodData>>>
    suspend fun getAllUnfinishedPeriods(): List<PeriodData>
    suspend fun update(period: PeriodData): PeriodData
    suspend fun add(period: PeriodData): PeriodData
}