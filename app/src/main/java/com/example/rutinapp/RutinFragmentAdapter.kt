package com.example.rutinapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rutinapp.Repo.Rutin
import com.example.rutinapp.custom.CustomLiveData
import com.example.rutinapp.databinding.FragmentRutinItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class RutinFragmentAdapter(var values: CustomLiveData<Rutin>) : RecyclerView.Adapter<RutinFragmentAdapter.ViewHolder>() {
//매개변수로 PlaceholderItem을 받음 --> PlaceholderContent의 Data class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
    }

//    override fun getItemCount(): Int = values.


    inner class ViewHolder(binding: FragmentRutinItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.title
        val contentView: TextView = binding.content
        val parent: LinearLayout = binding.root

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun getItemCount(): Int {
        return values.getCount()
    }

}