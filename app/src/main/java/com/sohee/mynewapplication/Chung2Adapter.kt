package com.sohee.mynewapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohee.mynewapplication.databinding.LayoutItemBinding
import com.sohee.mynewapplication.dialog.UpdateChung2
import com.sohee.mynewapplication.model.Chung2


class Chung2Adapter : RecyclerView.Adapter<Chung2Adapter.MyViewHolder>() {

    private var userList = emptyList<Chung2>()

    class MyViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        val currentId = currentItem.id.toString()
        val currentPlace = currentItem.place
        val currentFeeling = currentItem.feeling

        holder.binding.idText.text = currentId
        holder.binding.placeText.text = currentPlace
        holder.binding.feelingText.text = currentFeeling

        holder.binding.itemLayout.setOnClickListener {
            val intent = Intent(it.context, UpdateChung2::class.java)
            intent.putExtra("currentId", currentId)
            intent.putExtra("currentPlace", currentPlace)
            intent.putExtra("currentFeeling", currentFeeling)
            it.context.startActivity(intent)
        }
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return userList.size
    }

    // 유저 리스트 갱신
    fun setData(user : List<Chung2>){
        userList = user
        notifyDataSetChanged()
    }
}