package com.example.digital_kaf.di

import com.example.digital_kaf.data.repository.ActivityRepositoryImpl
import com.example.digital_kaf.data.repository.RegistrationRepositoryImpl
import com.example.digital_kaf.domain.repository.ActivityRepository
import com.example.digital_kaf.domain.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    @Singleton
    fun bindActivityRepository(repository: ActivityRepositoryImpl): ActivityRepository

    @Binds
    @Singleton
    fun bindRegistrationRepository(repository: RegistrationRepositoryImpl): RegistrationRepository
}