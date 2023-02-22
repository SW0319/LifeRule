package com.example.rutinapp.Repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RutinDao {

    @Query("SELECT * FROM RUTINList")
    fun getAllLists(): List<Rutin>

    @Query("insert into RutinList(title,content,monday,tueday,wedday,thuday,friday,satday,sunday) values('샘플 제목','샘플 내용',true,true,true,true,true,true,true)")
    fun addSampleData()

    @Insert
    fun add(rutin : Rutin)

    @Update
    fun update(rutin:Rutin)

    @Query("SELECT * from RUTINLIST where uid = :number")
    fun selectById(number:Int) : Rutin

}