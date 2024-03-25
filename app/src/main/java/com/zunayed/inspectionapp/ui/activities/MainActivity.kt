package com.zunayed.inspectionapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zunayed.inspectionapp.R
import com.zunayed.inspectionapp.data.DataModel
import com.zunayed.inspectionapp.databinding.ActivityMainBinding
import com.zunayed.inspectionapp.ui.adapters.MainListAdapter
import com.zunayed.inspectionapp.ui.fragment.DetailsBottomSheetDialogFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val itemList = mutableListOf<DataModel>()
    private lateinit var adapter: MainListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        // Create sample data
        loadData()

        // Initialize adapter with sample data
        adapter = MainListAdapter(itemList){
            val fragment = DetailsBottomSheetDialogFragment()
            fragment.show(supportFragmentManager, fragment.tag)
        }
        binding.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_button -> {
                // Handle button click
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadData() {
        itemList.add(DataModel("Weekly Inspection", "Holiday Inn Express Franklin","16, Dec"))
        itemList.add(DataModel("Above-Property Assessment", "Holiday Inn Express Franklin","18, Dec"))
        itemList.add(DataModel("Audit Shift Checklist", "Holiday Inn Express Franklin","18, Dec"))
        itemList.add(DataModel("Do Not Disturb - Daily Tracking List", "Holiday Inn Express Franklin","18, Dec"))
        itemList.add(DataModel("Do Not Disturb - Daily Tracking", "Holiday Inn Express Franklin","18, Dec"))
        itemList.add(DataModel("Annual Inspection", "Holiday Inn Express Franklin","18, Dec"))
        itemList.add(DataModel("Biennial Inspection", "Holiday Inn Express Franklin","18, Dec"))
        itemList.add(DataModel("Weekly Inspection", "Holiday Inn Express Franklin","18, Dec"))
    }
}