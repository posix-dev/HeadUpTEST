package com.example.headuptest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.headuptest.new_entry.data.DbEntity
import com.example.headuptest.new_entry.data.EntityDao

@Database(entities = [DbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entityDao(): EntityDao
}