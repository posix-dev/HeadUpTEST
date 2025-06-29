package com.example.headuptest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "carbs") val carbs: Int,
    @ColumnInfo(name = "proteins") val proteins: Int,
    @ColumnInfo(name = "fats") val fats: Int,
    @ColumnInfo(name = "calories") val calories: Int
)