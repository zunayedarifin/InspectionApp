package com.zunayed.inspectionapp.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.zunayed.inspectionapp.R
import com.zunayed.inspectionapp.data.model.SliderItems
import com.zunayed.inspectionapp.ui.adapters.SliderAdapter


class SliderActivity : AppCompatActivity() {
    private var sliderRunnable: Runnable? = null


    private val viewPager2: ViewPager2? = null
    private val sliderHandler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_slider)

        var close: ImageButton = findViewById(R.id.close)
        close.setOnClickListener {
            finish()
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // Initialize the sliderRunnable only after viewPager2 is initialized
        sliderRunnable = Runnable {
            viewPager2?.let {
                it.currentItem = (it.currentItem + 1) % it.adapter?.itemCount!!
            }
        }
        val viewPager2: ViewPager2 = findViewById(R.id.viewPagerImageSlider)

        val sliderItems = mutableListOf(
            SliderItems(R.drawable.image1),
            SliderItems(R.drawable.image2),
            SliderItems(R.drawable.image3)
        )

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        (viewPager2.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })

        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable!!)
                sliderHandler.postDelayed(sliderRunnable!!, 2000) // slide duration 2 seconds
            }
        })

    }

    override fun onPause() {
        super.onPause()
        sliderRunnable?.let { sliderHandler.removeCallbacks(it) }
    }

    override fun onResume() {
        super.onResume()
        sliderRunnable?.let { sliderHandler.postDelayed(it, 2000) }
    }

}