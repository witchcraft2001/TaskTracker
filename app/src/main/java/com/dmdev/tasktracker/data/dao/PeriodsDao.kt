package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.PeriodData

@Dao
interface PeriodsDao {
    @Insert
    suspend fun add(entity: PeriodData) : Long

    @Update
    suspend fun update(entity: PeriodData)

    @Query("SELECT * from ${PeriodData.TABLE_NAME}")
    suspend fun getAll(): List<PeriodData>

    @Query("SELECT * FROM ${PeriodData.TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): PeriodData?

    @Query("SELECT * FROM ${PeriodData.TABLE_NAME} WHERE ${PeriodData.PERIOD_ENDED_AT_FIELD} is null")
    fun getAllUnfinished(): List<PeriodData>
}