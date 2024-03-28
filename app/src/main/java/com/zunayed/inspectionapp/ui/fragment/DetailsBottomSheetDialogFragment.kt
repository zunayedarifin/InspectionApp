package com.zunayed.inspectionapp.ui.fragment


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zunayed.inspectionapp.R
import com.zunayed.inspectionapp.databinding.FragmentDetailsBottomSheetBinding
import com.zunayed.inspectionapp.ui.activities.SliderActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.thanel.swipeactionview.SwipeActionView
import me.thanel.swipeactionview.SwipeGestureListener

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

    private var canBeClicked = false
    private var initialX = 0f
    private var initialY = 0f
    val delayedSwipeGestureListener: SwipeGestureListener = object : SwipeGestureListener {
        override fun onSwipedHalfwayRight(swipeActionView: SwipeActionView): Boolean {
            return true
        }

        override fun onSwipedHalfwayLeft(swipeActionView: SwipeActionView): Boolean {
            return true
        }

        override fun onSwipeRightComplete(swipeActionView: SwipeActionView) {
            //this won't be called since onSwipedRight returns false
        }

        override fun onSwipeLeftComplete(swipeActionView: SwipeActionView) {
            //this won't be called since onSwipedLeft returns false
        }

        override fun onSwipedLeft(swipeActionView: SwipeActionView): Boolean {
            canBeClicked = true
//                swipeActionView.animateToOriginalPosition(2000)
            return false
        }

        override fun onSwipedRight(swipeActionView: SwipeActionView): Boolean {
//                swipeActionView.animateToOriginalPosition(2000)
            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        val bottomSheet =
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = bottomSheet?.let { BottomSheetBehavior.from(it) }
        if (behavior != null) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            dialog?.dismiss()
        }
        binding.floatingButton.setOnClickListener {
            dialog?.dismiss()
        }

        onCategoryClick()

        onActionClick()

        onLayoutClick()

        addSwipeGestureListeners(delayedSwipeGestureListener)
    }

    private fun onCategoryClick() {
        binding.cleanlinessLayout.setOnClickListener {
            if (isCleanlinessChecked) {
                binding.bedroomsLayout.visibility = View.GONE
                binding.firstList.visibility = View.GONE
                isCleanlinessChecked = false
                isBedroomChecked = false
            } else {
                binding.bedroomsLayout.visibility = View.VISIBLE
                isCleanlinessChecked = true
            }
            isFirstBedroomLayout = false
            isSecondBedroomLayout = false
            isThirdBedroomLayout = false
        }

        binding.bedroomsLayout.setOnClickListener {
            if (isBedroomChecked) {
                binding.firstList.visibility = View.GONE
                isBedroomChecked = false
            } else {
                binding.firstList.visibility = View.VISIBLE
                isBedroomChecked = true
            }
        }


        binding.amInspectionLayout.setOnClickListener {
            if (isAMInspectionChecked) {
                binding.livingAreasLayout.visibility = View.GONE
                binding.secondList.visibility = View.GONE
                isAMInspectionChecked = false
                isLivingAreaChecked = false
            } else {
                binding.livingAreasLayout.visibility = View.VISIBLE
                isAMInspectionChecked = true
            }
            isFirstLvLayout = false
            isSecondLvLayout = false
            isThirdLvLayout = false
        }

        binding.livingAreasLayout.setOnClickListener {
            if (isLivingAreaChecked) {
                binding.secondList.visibility = View.GONE
                isLivingAreaChecked = false
            } else {
                binding.secondList.visibility = View.VISIBLE
                isLivingAreaChecked = true
            }
            isFirstLvLayout = false
            isSecondLvLayout = false
            isThirdLvLayout = false
        }
    }

    private fun onLayoutClick() {
        binding.firstBedroomLayout.setOnClickListener {
            goToSliderActivity()
        }
        binding.secondBedroomLayout.setOnClickListener {
            goToSliderActivity()
        }
        binding.thirdBedroomLayout.setOnClickListener {
            goToSliderActivity()
        }

        binding.firstLVLayout.setOnClickListener {
            goToSliderActivity()
        }
        binding.secondLVLayout.setOnClickListener {
            goToSliderActivity()
        }
        binding.thirdLVLayout.setOnClickListener {
            goToSliderActivity()
        }
    }

    private fun onActionClick() {
        binding.firstBedroomLayoutAction.greenButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_600
                    )
                )
                isFirstBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.firstBedroomLayoutAction.redButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                isFirstBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.firstBedroomLayoutAction.orangeButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange
                    )
                )
                isFirstBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.firstBedroomLayoutAction.grayButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                isFirstBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }


        binding.secondBedroomLayoutAction.greenButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_600
                    )
                )
                isSecondBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()

        }
        binding.secondBedroomLayoutAction.redButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                isSecondBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.secondBedroomLayoutAction.orangeButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange
                    )
                )
                isSecondBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.secondBedroomLayoutAction.grayButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                isSecondBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }

        binding.thirdBedroomLayoutAction.greenButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_600
                    )
                )
                isThirdBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.thirdBedroomLayoutAction.redButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                isThirdBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.thirdBedroomLayoutAction.orangeButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange
                    )
                )
                isThirdBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.thirdBedroomLayoutAction.grayButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdBedroomLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                isThirdBedroomLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }


        binding.firstLVLayoutAction.greenButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_600
                    )
                )
                isFirstLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.firstLVLayoutAction.redButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                isFirstLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.firstLVLayoutAction.orangeButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange
                    )
                )
                isFirstLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.firstLVLayoutAction.grayButton.setOnClickListener {
            if (canBeClicked) {
                binding.firstLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                isFirstLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }


        binding.secondLVLayoutAction.greenButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_600
                    )
                )
                isSecondLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.secondLVLayoutAction.redButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                isSecondLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.secondLVLayoutAction.orangeButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange
                    )
                )
                isSecondLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.secondLVLayoutAction.grayButton.setOnClickListener {
            if (canBeClicked) {
                binding.secondLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                isSecondLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }

        binding.thirdLVLayoutAction.greenButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_600
                    )
                )
                isThirdLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.thirdLVLayoutAction.redButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                isThirdLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.thirdLVLayoutAction.orangeButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange
                    )
                )
                isThirdLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }
        binding.thirdLVLayoutAction.grayButton.setOnClickListener {
            if (canBeClicked) {
                binding.thirdLVLayoutValue.status.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
                isThirdLvLayout = false
                canBeClicked = false
            } else goToSliderActivity()
        }

    }

    private fun addSwipeGestureListeners(delayedSwipeGestureListener: SwipeGestureListener) {
        binding.firstBedroomLayout.activationDistanceRatio = 1f
        binding.firstBedroomLayout.swipeGestureListener = delayedSwipeGestureListener

        binding.secondBedroomLayout.activationDistanceRatio = 1f
        binding.secondBedroomLayout.swipeGestureListener = delayedSwipeGestureListener

        binding.thirdBedroomLayout.activationDistanceRatio = 1f
        binding.thirdBedroomLayout.swipeGestureListener = delayedSwipeGestureListener

        binding.firstLVLayout.activationDistanceRatio = 1f
        binding.firstLVLayout.swipeGestureListener = delayedSwipeGestureListener

        binding.secondLVLayout.activationDistanceRatio = 1f
        binding.secondLVLayout.swipeGestureListener = delayedSwipeGestureListener

        binding.thirdLVLayout.activationDistanceRatio = 1f
        binding.thirdLVLayout.swipeGestureListener = delayedSwipeGestureListener
    }

    private fun goToSliderActivity() {
        val intent = Intent(requireContext(), SliderActivity::class.java)
        startActivity(intent)
    }

    private fun showOptions(vararg views: LinearLayout) {
        views.forEach { it.visibility = LinearLayout.VISIBLE }
    }

    private fun hideOptions(vararg views: LinearLayout) {
        views.forEach { it.visibility = LinearLayout.GONE }
    }
}
