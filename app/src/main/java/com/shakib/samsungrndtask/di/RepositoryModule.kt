package com.shakib.samsungrndtask.di

import com.shakib.samsungrndtask.data.repository.RemoteRepositoryImpl
import com.shakib.samsungrndtask.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(remoteRepo: RemoteRepositoryImpl): Repository = remoteRepo
}
