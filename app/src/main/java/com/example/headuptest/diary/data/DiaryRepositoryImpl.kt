package com.example.headuptest.diary.data

import com.example.headuptest.diary.domain.repository.DiaryRepository
import com.example.headuptest.new_entry.data.EntityDao
import com.example.headuptest.new_entry.domain.entity.Entity
import com.example.headuptest.parameters.data.ParametersHandler
import com.example.headuptest.parameters.domain.entity.Parameters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val entityDao: EntityDao,
    private val parametersHandler: ParametersHandler,
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
        return parametersHandler.getParams()
    }
}