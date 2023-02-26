package com.example.rutinapp.placeholder

import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.RutinFragment
import com.example.rutinapp.RutinFragmentAdapter
import com.example.rutinapp.custom.CustomLiveData
import kotlinx.coroutines.*
import java.util.ArrayList

class RutinViewModel(context: Context, rutinFragment: RutinFragment){ //선언과 동시에 초기화, MVVM 디자인 패턴의 View Model

    val lists : CustomLiveData<Rutin> = CustomLiveData()
    var rutinFragment: RutinFragment

    init {
        readItem()
        this.rutinFragment = rutinFragment
    }

    fun addItem(item: Rutin) {

            lists.add(item)

        CoroutineScope(Dispatchers.IO).launch {
            DataModel.dao.add(item)
        }
            Log.e("test","아이템 추가")
    }

    fun updateItem(item: Rutin)
    {
        lists.update(item.uid,item)
        CoroutineScope(Dispatchers.IO).launch {
            DataModel.dao.update(item)
            Log.e("test","수정 완료")
        }

    }

    private fun readItem()
    {
        CoroutineScope(Dispatchers.IO).launch {
            lists.addAll(DataModel.dao.getAllLists())

        }
    }

    private fun createRutinItem(title: String, context: String, mon: Boolean = true, tue: Boolean  = true,
                                wed: Boolean = true,thu: Boolean = true,fri: Boolean = true,sat: Boolean = true,sun: Boolean  = true) : Rutin
    {
        return Rutin(
            title = title, content = context, monday = mon, tueday = tue, wedday = wed, thuday = thu, friday = fri, satday = sat, sunday = sun
        )
    }

    fun searchItem(uid: Int): Rutin {
        var rutin: Rutin

        runBlocking(Dispatchers.IO) {
            rutin =DataModel.dao.selectById(uid)
        }
        return rutin
    }
}