package com.example.headuptest.diary.data

import com.example.headuptest.db.EntityDao
import com.example.headuptest.diary.shared.DiaryRepository
import com.example.headuptest.model.Entity
import com.example.headuptest.model.Parameters
import com.example.headuptest.parameters.shared.ParametersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val entityDao: EntityDao,
    private val parametersRepository: ParametersRepository,
) : DiaryRepository {

    override fun getAllEntities(): Flow<List<Entity>> {
        return entityDao.getAll().map {
            it.map { entity ->
                Entity(
                    name = entity.name,
                    carbs = entity.carbs,
                    proteins = entity.proteins,
                    fats = entity.fats,
                    calories = entity.calories
                )
            }
        }
    }

    override fun getParams(): Parameters? {
        return parametersRepository.getParameters()
    }
}