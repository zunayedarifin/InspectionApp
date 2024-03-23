package com.zunayed.inspectionapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zunayed.inspectionapp.data.model.DropdownItem
import com.zunayed.inspectionapp.databinding.FragmentDetailsBottomSheetBinding
import com.zunayed.inspectionapp.ui.adapters.DropdownAdapter

class DetailsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the selected item data from arguments
        val selectedItem = arguments?.getString("selectedItem")

        val items = mutableListOf<DropdownItem>().apply {
            add(
                DropdownItem.HeaderItem(
                    title = "Header 1",
                    listItems = listOf(
                        DropdownItem.ListItem("Item 1"),
                        DropdownItem.ListItem("Item 2")
                    )
                )
            )
            add(
                DropdownItem.HeaderItem(
                    title = "Header 2",
                    listItems = listOf(
                        DropdownItem.ListItem("Item 3"),
                        DropdownItem.ListItem("Item 4")
                    )
                )
            )
        }

        val adapter = DropdownAdapter(items)
        binding.detailsRecyclerView.adapter = adapter
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Set click listener for close button
        binding.back.setOnClickListener {
            dismiss() // Close the bottom sheet
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}