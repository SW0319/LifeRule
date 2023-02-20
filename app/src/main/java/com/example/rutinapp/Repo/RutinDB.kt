package com.example.rutinapp.Repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Rutin::class], version = 1)
abstract class RutinDB : RoomDatabase() {
    abstract fun userDao() : RutinDao

}