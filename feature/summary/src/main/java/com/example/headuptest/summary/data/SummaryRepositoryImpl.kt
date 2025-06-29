package com.example.headuptest.summary.data

import com.example.headuptest.diary.shared.DiaryRepository
import com.example.headuptest.model.Entity
import com.example.headuptest.model.Parameters
import com.example.headuptest.parameters.shared.ParametersRepository
import com.example.headuptest.summary.domain.repository.SummaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val repository: ParametersRepository,
    private val diaryRepository: DiaryRepository,
) : SummaryRepository {

    override fun getEntities(): Flow<List<Entity>> = diaryRepository.getAllEntities()

    override fun getParameters(): Parameters? = repository.getParameters()


}