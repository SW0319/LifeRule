package com.example.rutinapp.placeholder

import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.custom.CustomLiveData
import kotlinx.coroutines.*
import java.util.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
class RutinViewModel(context: Context){ //선언과 동시에 초기화, MVVM 디자인 패턴의 View Model

    val lists : CustomLiveData<Rutin> = CustomLiveData()
    private val COUNT = 5  //item Count


    fun addItem(item: Rutin) {

            lists.add(item)

        CoroutineScope(Dispatchers.IO).launch {
            DataModel.dao.add(item)
        }
//          ITEMS.add(item) //이걸 꼭 넣어야되나....
            Log.e("test","아이템 추가")
    }

    fun updateItem(item: Rutin)
    {

        CoroutineScope(Dispatchers.IO).launch {
            DataModel.dao.update(item)
            Log.e("test","수정 완료")
        }
    }

    fun readItem()
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