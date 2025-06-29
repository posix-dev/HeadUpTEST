package com.example.headuptest.summary.domain.repository

import com.example.headuptest.model.Entity
import com.example.headuptest.model.Parameters
import kotlinx.coroutines.flow.Flow

interface SummaryRepository {
    fun getEntities(): Flow<List<Entity>>
    fun getParameters(): Parameters?
}