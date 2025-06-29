package com.example.headuptest.new_entry.data

import javax.inject.Inject
import com.example.headuptest.new_entry.domain.entity.Entity

class NewEntryRepository @Inject constructor(
    private val dao: EntityDao,
) {

    suspend fun insertEntry(entity: Entity) {
        dao.insert(
            DbEntity(
                name = entity.name,
                carbs = entity.carbs,
                proteins = entity.proteins,
                fats = entity.fats,
                calories = entity.calories
            )
        )
    }
}