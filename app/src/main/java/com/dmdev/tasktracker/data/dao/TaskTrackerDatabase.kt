package com.dmdev.tasktracker.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.data.entities.PeriodEntity
import com.dmdev.tasktracker.data.entities.TaskEntity

@Database(entities = [TaskEntity::class, PeriodEntity::class, CategoryEntity::class], version = 1)
abstract class TaskTrackerDatabase: RoomDatabase() {
    abstract fun tasksDao(): TasksDao
    abstract fun periodsDao(): PeriodsDao
    abstract fun categoriesDao(): CategoriesDao
}