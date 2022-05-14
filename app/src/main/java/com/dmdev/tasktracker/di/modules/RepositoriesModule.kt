package com.dmdev.tasktracker.di.modules

import com.dmdev.tasktracker.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun bindCategoriesRepository(
        categoriesRepositoryImpl: CategoriesRepositoryImpl
    ) : CategoriesRepository

    @Binds
    abstract fun bindPeriodsRepository(
        periodsRepositoryImpl: PeriodsRepositoryImpl
    ) : PeriodsRepository

    @Binds
    abstract fun bindTasksRepository(
        tasksRepositoryImpl: TasksRepositoryImpl
    ) : TasksRepository
}