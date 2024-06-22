package com.example.projectmobile

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var onboardingAdapter: FragmentStateAdapter
    private var currentPage = 0
    private val handler = Handler()
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            if (currentPage == onboardingAdapter.itemCount - 1) {
                // Stop auto scrolling when reaching the last page
                handler.removeCallbacks(this)
                return
            }
            currentPage++
            viewPager.setCurrentItem(currentPage, true)
            handler.postDelayed(this, 5000) // 5000 milliseconds = 5 seconds
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val layouts = listOf(
            R.layout.activity_onboarding1,
            R.layout.activity_onboarding2,
            R.layout.activity_onboarding3
        )

        viewPager = findViewById(R.id.viewPager)
        onboardingAdapter = OnboardingAdapter(this, layouts)
        viewPager.adapter = onboardingAdapter

        // Start auto scrolling
        handler.postDelayed(autoScrollRunnable, 5000) // Start scrolling after 5 seconds
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove callbacks to prevent memory leaks
        handler.removeCallbacks(autoScrollRunnable)
    }
}