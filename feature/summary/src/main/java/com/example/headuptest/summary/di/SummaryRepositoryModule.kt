package com.example.headuptest.summary.di

import com.example.headuptest.summary.data.SummaryRepositoryImpl
import com.example.headuptest.summary.domain.repository.SummaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SummaryRepositoryModule {
    @Binds
    abstract fun bindRepository(impl: SummaryRepositoryImpl): SummaryRepository
}