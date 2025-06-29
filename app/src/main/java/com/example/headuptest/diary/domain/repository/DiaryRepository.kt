package com.example.headuptest.diary.domain.repository

import com.example.headuptest.new_entry.domain.entity.Entity
import com.example.headuptest.parameters.domain.entity.Parameters
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    fun getAllEntities(): Flow<List<Entity>>
    fun getParams(): Parameters?
}