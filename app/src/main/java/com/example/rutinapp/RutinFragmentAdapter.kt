package com.example.rutinapp

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.custom.CustomLiveData
import com.example.rutinapp.databinding.FragmentRutinItemBinding
import com.example.rutinapp.placeholder.DataModel

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class RutinFragmentAdapter(var values: CustomLiveData<Rutin>) : RecyclerView.Adapter<RutinFragmentAdapter.ViewHolder>() {

    var selectedTextColor = R.color.purple_200
    var compareColor: Int = 0

//매개변수로 PlaceholderItem을 받음 --> PlaceholderContent의 Data class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    compareColor = ContextCompat.getColor(parent.context,selectedTextColor)
    //viewType 형태의 아이템 뷰를 위한 뷰홀더 객체를 생성한다.
        Log.e("test","MyRutinRecyclerViewAdapter.onCreateViewHolder()")
        return ViewHolder(

            FragmentRutinItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {

            }
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {  //position에 해당되는 데이터를 뷰홀더에 표기한다
        Log.e("test","MyRutinRecyclerViewAdapter.onBindViewHolder()")
        val item = values.get(position)
        holder.idView.text = item.title
        holder.contentView.text = item.content
        holder.itemID.text = item.uid.toString()
        if(item.monday)
            holder.monday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
        if(item.tueday)
            holder.tueday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
        if(item.wedday)
            holder.wedday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
        if(item.thuday)
            holder.thuday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
        if(item.friday)
            holder.friday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
        if(item.satday)
            holder.satday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
        if(item.sunday)
            holder.sunday.setTextColor(ContextCompat.getColor(holder.contentView.context,selectedTextColor))
    }


    inner class ViewHolder(binding: FragmentRutinItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            val rootView = binding.root.rootView
            rootView.setOnClickListener {
                Log.e("test","제목 : ${binding.title.text} 내용 : ${binding.content.text}")
            }

            rootView.setOnLongClickListener {
                val intent = Intent(rootView.context,DetailActivity::class.java)
//                Log.e("test","current : ${binding.RutinItemSun.currentTextColor} 이고 selected : ${ContextCompat.getColor(binding.itemParent.context,R.color.purple_200}")
                intent.putExtra("uid",binding.rutinItemId.text.toString().toInt())
                rootView.context.startActivity(intent)
                return@setOnLongClickListener(true)
            }

        }

        val idView: TextView = binding.title
        val contentView: TextView = binding.content
        val monday: TextView = binding.RutinItemMon
        val tueday: TextView = binding.RutinItemTue
        val wedday: TextView = binding.RutinItemWed
        val thuday: TextView = binding.RutinItemThu
        val friday: TextView = binding.RutinItemFri
        val satday: TextView = binding.RutinItemSat
        val sunday: TextView = binding.RutinItemSun
        val itemID: TextView = binding.rutinItemId
        //TODO 해야할 것은 루틴 추가한 후 바로 수정하려할 시 튕기는 에러 + 수정 시 요일도 바뀌도록 해야한다.

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun getItemCount(): Int {
        return values.getCount()
    }

}