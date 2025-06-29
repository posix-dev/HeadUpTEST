package com.example.headuptest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityDao {
    @Query("SELECT * FROM dbentity")
    fun getAll(): Flow<List<DbEntity>>

    @Insert
    suspend fun insert(user: DbEntity)
}