package com.example.rutinapp.placeholder

import android.content.Context
import android.util.Log
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.RutinFragment
import kotlinx.coroutines.*
import java.util.ArrayList

class RutinViewModel(context: Context, rutinFragment: RutinFragment){ //선언과 동시에 초기화, MVVM 디자인 패턴의 View Model

    val lists : ArrayList<Rutin> = ArrayList()
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
        RutinFragment.rutinFragmentAdapter.notifyItemInserted(lists.size)
    }

    fun updateItem(item: Rutin)
    {
        lists[item.uid-1] = item
        Log.e("test","lists[${item.uid-1}] = ${lists[item.uid-1].sunday} , ${lists[item.uid-1].monday} , ${lists[item.uid-1].tueday} , ${lists[item.uid-1].wedday} , " +
                "${lists[item.uid-1].thuday} , ${lists[item.uid-1].friday} , ${lists[item.uid-1].satday}")
        runBlocking(Dispatchers.IO) {
            DataModel.dao.update(item)
        }
        RutinFragment.rutinFragmentAdapter.notifyItemChanged(item.uid-1)

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