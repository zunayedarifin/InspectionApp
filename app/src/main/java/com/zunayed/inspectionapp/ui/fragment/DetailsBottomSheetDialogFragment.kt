package com.zunayed.inspectionapp.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnGroupExpandListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zunayed.inspectionapp.data.model.DropdownItem
import com.zunayed.inspectionapp.databinding.FragmentDetailsBottomSheetBinding
import com.zunayed.inspectionapp.ui.adapters.ThreeLevelListAdapter

class DetailsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!

    var parent: Array<String> = arrayOf("MOVIES", "GAMES")
    var movies: Array<String> = arrayOf("Horror", "Action", "Thriller/Drama")
    var games: Array<String> = arrayOf("Fps", "Moba", "Rpg", "Racing")
    val horror: Array<String> = arrayOf("Conjuring", "Insidious", "The Ring")
    var action: Array<String> = arrayOf("Jon Wick", "Die Hard", "Fast 7", "Avengers")
    var thriller: Array<String> = arrayOf(
        "Imitation Game",
        "Tinker, Tailer, Soldier, Spy",
        "Inception",
        "Manchester by the Sea"
    )
    var fps: Array<String> =
        arrayOf("CS: GO", "Team Fortress 2", "Overwatch", "Battlefield 1", "Halo II", "Warframe")
    var moba: Array<String> =
        arrayOf("Dota 2", "League of Legends", "Smite", "Strife", "Heroes of the Storm")
    var rpg: Array<String> = arrayOf(
        "Witcher III",
        "Skyrim",
        "Warcraft",
        "Mass Effect II",
        "Diablo",
        "Dark Souls",
        "Last of Us"
    )
    var racing: Array<String> =
        arrayOf("NFS: Most Wanted", "Forza Motorsport 3", "EA: F1 2016", "Project Cars")

    var thirdLevelMovies: LinkedHashMap<String, Array<String>> = LinkedHashMap()
    var thirdLevelGames: LinkedHashMap<String, Array<String>> = LinkedHashMap()

    val secondLevel: MutableList<Array<String>> = mutableListOf()
    val data: MutableList<LinkedHashMap<String, Array<String>>> = mutableListOf()

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


        secondLevel.add(movies)
        secondLevel.add(games)

        thirdLevelMovies.put(movies.get(0), horror)
        thirdLevelMovies.put(movies.get(1), action)
        thirdLevelMovies.put(movies.get(2), thriller)

        thirdLevelGames.put(games.get(0), fps)
        thirdLevelGames.put(games.get(1), moba)
        thirdLevelGames.put(games.get(2), rpg)
        thirdLevelGames.put(games.get(3), racing)

        data.add(thirdLevelMovies)
        data.add(thirdLevelGames)


        // parent adapter
        val threeLevelListAdapterAdapter: ThreeLevelListAdapter =
            ThreeLevelListAdapter(requireContext(), parent, secondLevel, data)


        // set adapter
        binding.expandibleListview.setAdapter(threeLevelListAdapterAdapter)


        // OPTIONAL : Show one list at a time
        binding.expandibleListview.setOnGroupExpandListener(object : OnGroupExpandListener {
            var previousGroup: Int = -1

            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup) binding.expandibleListview.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }
        })

//
//        val adapter = DropdownAdapter(items)
//        binding.detailsRecyclerView.adapter = adapter
//        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())


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