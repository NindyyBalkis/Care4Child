package com.example.projectmobile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(private val activity: FragmentActivity, private val layouts: List<Int>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return layouts.size
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment.newInstance(layouts[position])
    }
}