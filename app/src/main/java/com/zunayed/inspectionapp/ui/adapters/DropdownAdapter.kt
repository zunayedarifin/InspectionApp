package com.zunayed.inspectionapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zunayed.inspectionapp.data.model.DropdownItem
import com.zunayed.inspectionapp.databinding.DropdownHeaderItemBinding
import com.zunayed.inspectionapp.databinding.DropdownListItemBinding

class DropdownAdapter(private val initialItems: List<DropdownItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_VIEW_TYPE = 0
    private val LIST_VIEW_TYPE = 1
    private val items: MutableList<DropdownItem> = initialItems.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE -> HeaderViewHolder(DropdownHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            LIST_VIEW_TYPE -> ListViewHolder(DropdownListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val headerItem = items[position] as DropdownItem.HeaderItem
                holder.bind(headerItem)
                holder.itemView.setOnClickListener {
                    // Toggle visibility of list items
                    headerItem.isExpanded = !headerItem.isExpanded
                    updateListItems()
                }
            }
            is ListViewHolder -> {
                val listItem = items[position] as DropdownItem.ListItem
                holder.bind(listItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DropdownItem.HeaderItem -> HEADER_VIEW_TYPE
            is DropdownItem.ListItem -> LIST_VIEW_TYPE
        }
    }

    inner class HeaderViewHolder(private val binding: DropdownHeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(headerItem: DropdownItem.HeaderItem) {
            binding.headerTextView.text = headerItem.title
        }
    }

    inner class ListViewHolder(private val binding: DropdownListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: DropdownItem.ListItem) {
            binding.listTextView.text = listItem.title
        }
    }

    private fun updateListItems() {
        items.clear()
        initialItems.forEach { item ->
            items.add(item)
            if (item is DropdownItem.HeaderItem && item.isExpanded) {
                items.addAll(item.listItems)
            }
        }
        notifyDataSetChanged()
    }
}
