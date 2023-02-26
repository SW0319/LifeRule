package com.example.rutinapp.placeholder

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.room.Room
import com.example.rutinapp.Repo.RutinDB
import com.example.rutinapp.Repo.RutinDao
import com.example.rutinapp.RutinFragment

object DataModel {

    lateinit var db: RutinDB
    lateinit var dao: RutinDao
    lateinit var rutinViewModel: RutinViewModel

    fun init(context: Context, fragment: RutinFragment)
    {
        db = Room.databaseBuilder(context,RutinDB::class.java,"rutindb").build()
        dao = db.userDao()
        rutinViewModel = RutinViewModel(context,fragment)
    }




}