package com.dmdev.tasktracker.di.modules

import android.content.Context
import androidx.room.Room
import com.dmdev.tasktracker.BuildConfig
import com.dmdev.tasktracker.data.dao.CategoriesDao
import com.dmdev.tasktracker.data.dao.PeriodsDao
import com.dmdev.tasktracker.data.dao.TaskTrackerDatabase
import com.dmdev.tasktracker.data.dao.TasksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): TaskTrackerDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            TaskTrackerDatabase::class.java,
            "tasks_db"
        ).apply {
            if (BuildConfig.DEBUG) {
                fallbackToDestructiveMigration()
            }
        }
            .build()

    @Provides
    @Singleton
    fun provideTasksDao(database: TaskTrackerDatabase): TasksDao = database.tasksDao()

    @Provides
    @Singleton
    fun providePeriodsDao(database: TaskTrackerDatabase): PeriodsDao = database.periodsDao()

    @Provides
    @Singleton
    fun provideCategoriesDao(database: TaskTrackerDatabase): CategoriesDao = database.categoriesDao()
}