package com.carousell.autonotifylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carousell.autonotify.AutoNotifyList
import com.carousell.autonotifylist.databinding.AdapterMainBinding

interface OnRemoveListener<T> {
    fun onRemove(item: T)
}

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>(), OnRemoveListener<Int> {

    val list = AutoNotifyList<Int>(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(AdapterMainBinding.inflate(layoutInflater, parent, false), this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onRemove(item: Int) {
        list.remove(item)
    }

    class MyViewHolder(
        private val binding: AdapterMainBinding,
        private val onRemoveListener: OnRemoveListener<Int>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: Int) {
            binding.textView.text = value.toString()
            binding.root.setOnClickListener {
                onRemoveListener.onRemove(value)
            }
        }
    }
}