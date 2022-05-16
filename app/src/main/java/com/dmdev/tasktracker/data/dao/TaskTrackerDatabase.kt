package com.dmdev.tasktracker.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.data.data.TaskData

@Database(entities = [TaskData::class, PeriodData::class, CategoryData::class], version = 1)
abstract class TaskTrackerDatabase: RoomDatabase() {
    abstract fun tasksDao(): TasksDao
    abstract fun periodsDao(): PeriodsDao
    abstract fun categoriesDao(): CategoriesDao
}