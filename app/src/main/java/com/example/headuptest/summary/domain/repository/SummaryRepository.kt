package com.example.headuptest.summary.domain.repository

import com.example.headuptest.new_entry.domain.entity.Entity
import com.example.headuptest.parameters.domain.entity.Parameters
import kotlinx.coroutines.flow.Flow

interface SummaryRepository {
    fun getEntities(): Flow<List<Entity>>
    fun getParameters(): Parameters?
}