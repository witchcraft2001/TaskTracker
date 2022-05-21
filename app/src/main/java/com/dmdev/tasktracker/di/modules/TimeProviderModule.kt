package com.dmdev.tasktracker.di.modules

import com.dmdev.tasktracker.ui.utils.time_provider.TimeProvider
import com.dmdev.tasktracker.ui.utils.time_provider.TimeProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeProviderModule {
    @Binds
    abstract fun bindTimeProvider(impl: TimeProviderImpl) : TimeProvider
}