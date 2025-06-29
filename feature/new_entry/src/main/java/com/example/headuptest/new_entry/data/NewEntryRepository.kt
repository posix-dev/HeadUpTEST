package com.example.headuptest.new_entry.data

import com.example.headuptest.db.DbEntity
import com.example.headuptest.db.EntityDao
import com.example.headuptest.model.Entity
import javax.inject.Inject

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