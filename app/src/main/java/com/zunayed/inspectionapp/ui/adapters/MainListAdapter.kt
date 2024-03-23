package com.zunayed.inspectionapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zunayed.inspectionapp.data.DataModel
import com.zunayed.inspectionapp.databinding.MainItemListBinding

class MainListAdapter(private val itemList: List<DataModel>, private val onItemClick: (DataModel) -> Unit) :
    RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(private val binding: MainItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataModel) {
            binding.title.text = item.title
            binding.description.text = item.description
            binding.date.text = item.date

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}