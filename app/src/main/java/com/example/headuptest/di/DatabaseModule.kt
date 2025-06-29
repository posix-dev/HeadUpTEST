package com.example.headuptest.di

import android.content.Context
import androidx.room.Room
import com.example.headuptest.db.AppDatabase
import com.example.headuptest.db.EntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        val db = Room.databaseBuilder(
            context, AppDatabase::class.java, "database-name"
        ).build()

        return db
    }

    @Provides
    @Singleton
    fun provideEntityDao(db: AppDatabase): EntityDao {
        return db.entityDao()
    }
}