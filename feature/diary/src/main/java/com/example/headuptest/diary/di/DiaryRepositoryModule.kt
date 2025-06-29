package com.example.headuptest.diary.di

import com.example.headuptest.diary.data.DiaryRepositoryImpl
import com.example.headuptest.diary.shared.DiaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DiaryRepositoryModule {
    @Binds
    abstract fun bindRepository(impl: DiaryRepositoryImpl): DiaryRepository
}