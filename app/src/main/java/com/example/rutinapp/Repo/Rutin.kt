package com.example.rutinapp.Repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RutinList")
data class Rutin(

    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var title: String?,
    var content: String?,
    var monday: Boolean = true,
    var tueday: Boolean = true,
    var wedday: Boolean = true,
    var thuday: Boolean = true,
    var friday: Boolean = true,
    var satday: Boolean = true,
    var sunday: Boolean = true,
)
