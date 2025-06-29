package com.example.headuptest.diary.shared

import com.example.headuptest.model.Entity
import com.example.headuptest.model.Parameters
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    fun getAllEntities(): Flow<List<Entity>>
    fun getParams(): Parameters?
}