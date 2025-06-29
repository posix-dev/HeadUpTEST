package com.example.headuptest.summary.data

import com.example.headuptest.diary.data.DiaryRepositoryImpl
import com.example.headuptest.new_entry.domain.entity.Entity
import com.example.headuptest.parameters.domain.ParametersRepository
import com.example.headuptest.parameters.domain.entity.Parameters
import com.example.headuptest.summary.domain.repository.SummaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val repository: ParametersRepository,
    private val diaryRepositoryImpl: DiaryRepositoryImpl,
) : SummaryRepository {
    override fun getEntities(): Flow<List<Entity>> = diaryRepositoryImpl.getAllEntities()

    override fun getParameters(): Parameters? = repository.getParameters()
}