package com.example.headuptest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entityDao(): EntityDao
}