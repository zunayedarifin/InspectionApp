package com.zunayed.inspectionapp.data.model

sealed class DropdownItem {
    data class HeaderItem(val title: String, var isExpanded: Boolean = false, val listItems: List<ListItem>) : DropdownItem()
    data class ListItem(val title: String) : DropdownItem()
}