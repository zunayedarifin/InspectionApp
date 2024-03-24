package com.zunayed.inspectionapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.TextView
import com.zunayed.inspectionapp.R

class ThreeLevelListAdapter(
    private val context: Context,
    var parentHeaders: Array<String>,
    var secondLevel: List<Array<String>>,
    var data: List<LinkedHashMap<String, Array<String>>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return parentHeaders.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        // no idea why this code is working


        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupPosition
    }

    override fun getChild(group: Int, child: Int): Any {
        return child
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = inflater.inflate(R.layout.row_first, parent, false)
        val text = convertView.findViewById<View>(R.id.rowParentText) as TextView
        text.text = parentHeaders[groupPosition]

        return convertView
    }


    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val secondLevelELV: SecondLevelExpandableListView = SecondLevelExpandableListView(context)

        val headers = secondLevel[groupPosition]


        val childData: MutableList<Array<String>> = ArrayList()
        val secondLevelData: HashMap<String, Array<String>> = data[groupPosition]

        for (key in secondLevelData.keys) {
            childData.add(secondLevelData[key]!!)
        }



        secondLevelELV.setAdapter(SecondLevelAdapter(context, headers, childData))

        secondLevelELV.setGroupIndicator(null)


        secondLevelELV.setOnGroupExpandListener(object : OnGroupExpandListener {
            var previousGroup: Int = -1

            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup) secondLevelELV.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }
        })


        return secondLevelELV
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
