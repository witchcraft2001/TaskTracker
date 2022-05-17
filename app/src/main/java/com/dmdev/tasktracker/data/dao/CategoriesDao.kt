package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.entities.CategoryEntity

@Dao
interface CategoriesDao {
    @Insert
    suspend fun add(entity: CategoryEntity)

    @Update
    suspend fun update(entity: CategoryEntity)

    @Query("SELECT * from ${CategoryEntity.TABLE_NAME}")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): CategoryEntity?
}