package com.dmdev.tasktracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dmdev.tasktracker.data.data.CategoryData

@Dao
interface CategoriesDao {
    @Insert
    suspend fun add(entity: CategoryData)

    @Update
    suspend fun update(entity: CategoryData)

    @Query("SELECT * from ${CategoryData.TABLE_NAME}")
    suspend fun getAll(): List<CategoryData>

    @Query("SELECT * FROM ${CategoryData.TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): CategoryData?
}