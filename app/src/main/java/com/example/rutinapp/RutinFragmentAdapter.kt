package com.example.rutinapp

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.rutinapp.Repo.Rutin


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class RutinFragmentAdapter(var values: ArrayList<Rutin>) : RecyclerView.Adapter<RutinFragmentAdapter.ViewHolder>() {

    var selectedTextColor = R.color.purple_200
    var notSelectedTextColor = R.color.black
    var compareColor: Int = 0

//매개변수로 PlaceholderItem을 받음 --> PlaceholderContent의 Data class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    compareColor = ContextCompat.getColor(parent.context,selectedTextColor)
    //viewType 형태의 아이템 뷰를 위한 뷰홀더 객체를 생성한다.
        Log.e("test","MyRutinRecyclerViewAdapter.onCreateViewHolder()")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_rutin_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {  //position에 해당되는 데이터를 뷰홀더에 표기한다
        Log.e("test","MyRutinRecyclerViewAdapter.onBindViewHolder()")
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.content
        holder.itemID.text = item.uid.toString()

        var dayLists = arrayOf(item.monday, item.tueday, item.wedday, item.thuday, item.friday, item.satday, item.sunday)
        var holderLists = arrayOf(holder.monday, holder.tueday, holder.wedday, holder.thuday, holder.friday, holder.satday, holder.sunday)
        for (i in 0..6)
        {
            if(dayLists[i])
                holderLists[i].setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
            else
                holderLists[i].setTextColor(ContextCompat.getColor(holder.contentView.context,notSelectedTextColor))
        }

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            val rootView = view.rootView
            rootView.setOnClickListener {
//                Log.e("test","제목 : ${} 내용 : ${binding.content.text}")
            }

            rootView.setOnLongClickListener {
                val intent = Intent(rootView.context,DetailActivity::class.java)
//                Log.e("test","current : ${binding.RutinItemSun.currentTextColor} 이고 selected : ${ContextCompat.getColor(binding.itemParent.context,R.color.purple_200}")
                intent.putExtra("uid",view.findViewById<TextView>(R.id.rutinItem_id).text.toString().toInt())
                rootView.context.startActivity(intent)
                return@setOnLongClickListener(true)
            }

        }

        val idView: TextView = view.findViewById(R.id.title)
        val contentView: TextView = view.findViewById(R.id.content)
        val monday: TextView = view.findViewById(R.id.RutinItem_mon)
        val tueday: TextView = view.findViewById(R.id.RutinItem_tue)
        val wedday: TextView = view.findViewById(R.id.RutinItem_wed)
        val thuday: TextView = view.findViewById(R.id.RutinItem_thu)
        val friday: TextView = view.findViewById(R.id.RutinItem_fri)
        val satday: TextView = view.findViewById(R.id.RutinItem_sat)
        val sunday: TextView = view.findViewById(R.id.RutinItem_sun)
        val itemID: TextView = view.findViewById(R.id.rutinItem_id)
        //TODO 해야할 것은 루틴 추가한 후 바로 수정하려할 시 튕기는 에러 + 수정 시 요일도 바뀌도록 해야한다.

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

}