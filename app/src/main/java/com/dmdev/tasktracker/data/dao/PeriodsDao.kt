package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.entities.PeriodEntity

@Dao
interface PeriodsDao {
    @Insert
    suspend fun add(entity: PeriodEntity) : Long

    @Update
    suspend fun update(entity: PeriodEntity)

    @Query("SELECT * from ${PeriodEntity.TABLE_NAME}")
    suspend fun getAll(): List<PeriodEntity>

    @Query("SELECT * FROM ${PeriodEntity.TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): PeriodEntity?

    @Query("SELECT * FROM ${PeriodEntity.TABLE_NAME} WHERE ${PeriodEntity.PERIOD_ENDED_AT_FIELD} is null")
    fun getAllUnfinished(): List<PeriodEntity>
}