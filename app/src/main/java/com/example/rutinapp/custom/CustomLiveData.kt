package com.example.rutinapp.custom

import androidx.lifecycle.MutableLiveData

//Mutable : 접근 가능한
//LiveData : 변화를 관찰하는 데이터 홀더

class CustomLiveData<T> : MutableLiveData<MutableList<T>>(){

    val temp = mutableListOf<T>()   //Rutin List


    init {
        value = temp    // livedata 지정 (observe 변화 감지됨)
    }

    fun add(item: T)
    {
        temp.add(item)  //데이터 추가
        value = temp    // livedata 지정 (observe 변화 감지됨)
    }

    fun addAll(item: List<T>)
    {
        temp.addAll(item)  //데이터 추가
    }

    fun get(int: Int) : T
    {
        return temp.get(int)
    }

    fun getCount() : Int
    {
        return temp.size
    }

}