package com.carousell.autonotifylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carousell.autonotifylist.databinding.AdapterMainBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val list = AutoNotifyList.bind(mutableListOf<Int>(), this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(AdapterMainBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(private val binding: AdapterMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(value: Int) {
            binding.textView.text = value.toString()
        }
    }
}