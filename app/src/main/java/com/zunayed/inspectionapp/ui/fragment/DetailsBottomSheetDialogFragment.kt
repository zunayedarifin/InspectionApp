package com.zunayed.inspectionapp.ui.fragment


import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zunayed.inspectionapp.R
import com.zunayed.inspectionapp.databinding.FragmentDetailsBottomSheetBinding

class DetailsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var isCleanlinessChecked = false
    private var isBedroomChecked = false
    private var isLivingAreaChecked = false
    private var isAMInspectionChecked = false
    private var isFirstBedroomLayout = false
    private var isSecondBedroomLayout = false
    private var isThirdBedroomLayout = false
    private var isFirstLvLayout = false
    private var isSecondLvLayout = false
    private var isThirdLvLayout = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        // Set fullscreen
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // Adjust BottomSheetDialog behavior
        val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = bottomSheet?.let { BottomSheetBehavior.from(it) }
        if (behavior != null) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            dialog?.dismiss()
        }
        binding.floatingButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.cleanlinessLayout.setOnClickListener {
            if (isCleanlinessChecked){
                binding.bedroomsLayout.visibility = View.GONE
                binding.firstBedroomLayout.root.visibility = View.GONE
                binding.secondBedroomLayout.root.visibility = View.GONE
                binding.thirdBedroomLayout.root.visibility = View.GONE
                isCleanlinessChecked = false
                isBedroomChecked = false
            }else{
                binding.bedroomsLayout.visibility = View.VISIBLE
                isCleanlinessChecked = true
            }
            binding.firstBedroomLayoutAction.root.visibility = View.GONE
            binding.secondBedroomLayoutAction.root.visibility = View.GONE
            binding.thirdBedroomLayoutAction.root.visibility = View.GONE
            isFirstBedroomLayout = false
            isSecondBedroomLayout = false
            isThirdBedroomLayout = false
        }

        binding.bedroomsLayout.setOnClickListener {
            if (isBedroomChecked){
                binding.firstBedroomLayout.root.visibility = View.GONE
                binding.secondBedroomLayout.root.visibility = View.GONE
                binding.thirdBedroomLayout.root.visibility = View.GONE
                isBedroomChecked = false
            }else{
                binding.firstBedroomLayout.root.visibility = View.VISIBLE
                binding.secondBedroomLayout.root.visibility = View.VISIBLE
                binding.thirdBedroomLayout.root.visibility = View.VISIBLE
                isBedroomChecked = true
            }
            binding.firstBedroomLayoutAction.root.visibility = View.GONE
            binding.secondBedroomLayoutAction.root.visibility = View.GONE
            binding.thirdBedroomLayoutAction.root.visibility = View.GONE
            isFirstBedroomLayout = false
            isSecondBedroomLayout = false
            isThirdBedroomLayout = false
        }


        binding.amInspectionLayout.setOnClickListener {
            if (isAMInspectionChecked){
                binding.livingAreasLayout.visibility = View.GONE
                binding.firstLVLayout.root.visibility = View.GONE
                binding.secondLVLayout.root.visibility = View.GONE
                binding.thirdLVLayout.root.visibility = View.GONE
                isAMInspectionChecked = false
                isLivingAreaChecked = false
            }else{
                binding.livingAreasLayout.visibility = View.VISIBLE
                isAMInspectionChecked = true
            }
            binding.firstLVLayoutAction.root.visibility = View.GONE
            binding.secondLVLayoutAction.root.visibility = View.GONE
            binding.thirdLVLayoutAction.root.visibility = View.GONE
            isFirstLvLayout = false
            isSecondLvLayout = false
            isThirdLvLayout = false
        }

        binding.livingAreasLayout.setOnClickListener {
            if (isLivingAreaChecked){
                binding.firstLVLayout.root.visibility = View.GONE
                binding.secondLVLayout.root.visibility = View.GONE
                binding.thirdLVLayout.root.visibility = View.GONE
                isLivingAreaChecked = false
            }else{
                binding.firstLVLayout.root.visibility = View.VISIBLE
                binding.secondLVLayout.root.visibility = View.VISIBLE
                binding.thirdLVLayout.root.visibility = View.VISIBLE
                isLivingAreaChecked = true
            }
            binding.firstLVLayoutAction.root.visibility = View.GONE
            binding.secondLVLayoutAction.root.visibility = View.GONE
            binding.thirdLVLayoutAction.root.visibility = View.GONE
            isFirstLvLayout = false
            isSecondLvLayout = false
            isThirdLvLayout = false
        }

        binding.firstBedroomLayout.root.setOnLongClickListener {
            if (isFirstBedroomLayout){
                binding.firstBedroomLayoutAction.root.visibility = View.GONE
                isFirstBedroomLayout = false
            }else{
                binding.firstBedroomLayoutAction.root.visibility = View.VISIBLE
                isFirstBedroomLayout = true
            }
            // Perform action on long click
            true // Return true to consume the long click event
        }
        binding.secondBedroomLayout.root.setOnLongClickListener {
            if (isSecondBedroomLayout){
                binding.secondBedroomLayoutAction.root.visibility = View.GONE
                isSecondBedroomLayout = false
            }else{
                binding.secondBedroomLayoutAction.root.visibility = View.VISIBLE
                isSecondBedroomLayout = true
            }

            // Perform action on long click
            true // Return true to consume the long click event
        }
        binding.thirdBedroomLayout.root.setOnLongClickListener {
            if (isThirdBedroomLayout){
                binding.thirdBedroomLayoutAction.root.visibility = View.GONE
                isThirdBedroomLayout = false
            }else{
                binding.thirdBedroomLayoutAction.root.visibility = View.VISIBLE
                isThirdBedroomLayout = true
            }

            // Perform action on long click
            true // Return true to consume the long click event
        }



        binding.firstLVLayout.root.setOnLongClickListener {
            if (isFirstLvLayout){
                binding.firstLVLayoutAction.root.visibility = View.GONE
                isFirstLvLayout = false
            }else{
                binding.firstLVLayoutAction.root.visibility = View.VISIBLE
                isFirstLvLayout = true
            }
            // Perform action on long click
            true // Return true to consume the long click event
        }
        binding.secondLVLayout.root.setOnLongClickListener {
            if (isSecondLvLayout){
                binding.secondLVLayoutAction.root.visibility = View.GONE
                isSecondLvLayout = false
            }else{
                binding.secondLVLayoutAction.root.visibility = View.VISIBLE
                isSecondLvLayout = true
            }

            // Perform action on long click
            true // Return true to consume the long click event
        }
        binding.thirdLVLayout.root.setOnLongClickListener {
            if (isThirdLvLayout){
                binding.thirdLVLayoutAction.root.visibility = View.GONE
                isThirdLvLayout = false
            }else{
                binding.thirdLVLayoutAction.root.visibility = View.VISIBLE
                isThirdLvLayout = true
            }

            // Perform action on long click
            true // Return true to consume the long click event
        }

        binding.firstBedroomLayoutAction.greenButton.setOnClickListener {
            binding.firstBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_600))
            isFirstBedroomLayout = false
            binding.firstBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.firstBedroomLayoutAction.redButton.setOnClickListener {
            binding.firstBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            isFirstBedroomLayout = false
            binding.firstBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.firstBedroomLayoutAction.orangeButton.setOnClickListener {
            binding.firstBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            isFirstBedroomLayout = false
            binding.firstBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.firstBedroomLayoutAction.grayButton.setOnClickListener {
            binding.firstBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            isFirstBedroomLayout = false
            binding.firstBedroomLayoutAction.root.visibility = View.GONE
        }


        binding.secondBedroomLayoutAction.greenButton.setOnClickListener {
            binding.secondBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_600))
            isSecondBedroomLayout = false
            binding.secondBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.secondBedroomLayoutAction.redButton.setOnClickListener {
            binding.secondBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            isSecondBedroomLayout = false
            binding.secondBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.secondBedroomLayoutAction.orangeButton.setOnClickListener {
            binding.secondBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            isSecondBedroomLayout = false
            binding.secondBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.secondBedroomLayoutAction.grayButton.setOnClickListener {
            binding.secondBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            isSecondBedroomLayout = false
            binding.secondBedroomLayoutAction.root.visibility = View.GONE
        }

        binding.thirdBedroomLayoutAction.greenButton.setOnClickListener {
            binding.thirdBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_600))
            isThirdBedroomLayout = false
            binding.thirdBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.thirdBedroomLayoutAction.redButton.setOnClickListener {
            binding.thirdBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            isThirdBedroomLayout = false
            binding.thirdBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.thirdBedroomLayoutAction.orangeButton.setOnClickListener {
            binding.thirdBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            isThirdBedroomLayout = false
            binding.thirdBedroomLayoutAction.root.visibility = View.GONE
        }
        binding.thirdBedroomLayoutAction.grayButton.setOnClickListener {
            binding.thirdBedroomLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            isThirdBedroomLayout = false
            binding.thirdBedroomLayoutAction.root.visibility = View.GONE
        }


        ////////


        binding.firstLVLayoutAction.greenButton.setOnClickListener {
            binding.firstLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_600))
            isFirstLvLayout = false
            binding.firstLVLayoutAction.root.visibility = View.GONE
        }
        binding.firstLVLayoutAction.redButton.setOnClickListener {
            binding.firstLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            isFirstLvLayout = false
            binding.firstLVLayoutAction.root.visibility = View.GONE
        }
        binding.firstLVLayoutAction.orangeButton.setOnClickListener {
            binding.firstLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            isFirstLvLayout = false
            binding.firstLVLayoutAction.root.visibility = View.GONE
        }
        binding.firstLVLayoutAction.grayButton.setOnClickListener {
            binding.firstLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            isFirstLvLayout = false
            binding.firstLVLayoutAction.root.visibility = View.GONE
        }


        binding.secondLVLayoutAction.greenButton.setOnClickListener {
            binding.secondLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_600))
            isSecondLvLayout = false
            binding.secondLVLayoutAction.root.visibility = View.GONE
        }
        binding.secondLVLayoutAction.redButton.setOnClickListener {
            binding.secondLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            isSecondLvLayout = false
            binding.secondLVLayoutAction.root.visibility = View.GONE
        }
        binding.secondLVLayoutAction.orangeButton.setOnClickListener {
            binding.secondLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            isSecondLvLayout = false
            binding.secondLVLayoutAction.root.visibility = View.GONE
        }
        binding.secondLVLayoutAction.grayButton.setOnClickListener {
            binding.secondLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            isSecondLvLayout = false
            binding.secondLVLayoutAction.root.visibility = View.GONE
        }

        binding.thirdLVLayoutAction.greenButton.setOnClickListener {
            binding.thirdLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_600))
            isThirdLvLayout = false
            binding.thirdLVLayoutAction.root.visibility = View.GONE
        }
        binding.thirdLVLayoutAction.redButton.setOnClickListener {
            binding.thirdLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            isThirdLvLayout = false
            binding.thirdLVLayoutAction.root.visibility = View.GONE
        }
        binding.thirdLVLayoutAction.orangeButton.setOnClickListener {
            binding.thirdLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
            isThirdLvLayout = false
            binding.thirdLVLayoutAction.root.visibility = View.GONE
        }
        binding.thirdLVLayoutAction.grayButton.setOnClickListener {
            binding.thirdLVLayout.status.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            isThirdLvLayout = false
            binding.thirdLVLayoutAction.root.visibility = View.GONE
        }

    }
}
