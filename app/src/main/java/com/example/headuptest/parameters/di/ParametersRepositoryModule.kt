package com.example.headuptest.parameters.di

import com.example.headuptest.parameters.data.ParametersRepositoryImpl
import com.example.headuptest.parameters.domain.ParametersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ParametersRepositoryModule {
    @Binds
    abstract fun bindRepository(impl: ParametersRepositoryImpl): ParametersRepository
}