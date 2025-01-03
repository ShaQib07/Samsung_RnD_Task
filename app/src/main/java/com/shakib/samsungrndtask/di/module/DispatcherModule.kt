package com.shakib.samsungrndtask.di.module

import com.shakib.samsungrndtask.di.qualifier.DefaultDispatcher
import com.shakib.samsungrndtask.di.qualifier.IoDispatcher
import com.shakib.samsungrndtask.di.qualifier.MainDispatcher
import com.shakib.samsungrndtask.di.qualifier.MainImmediateDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    @Singleton
    fun provideIoDispatcher() = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    @Singleton
    fun provideDefaultDispatcher() = Dispatchers.Default

    @MainDispatcher
    @Provides
    @Singleton
    fun provideMainDispatcher() = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    @Singleton
    fun provideMainImmediateDispatcher() = Dispatchers.Main.immediate
}