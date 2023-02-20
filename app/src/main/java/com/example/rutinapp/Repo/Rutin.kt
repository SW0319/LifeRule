package com.example.rutinapp.Repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RutinList")
data class Rutin(

    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val title: String?,
    val content: String?,
    val monday: Boolean = true,
    val tueday: Boolean = true,
    val wedday: Boolean = true,
    val thuday: Boolean = true,
    val friday: Boolean = true,
    val satday: Boolean = true,
    val sunday: Boolean = true,
)
