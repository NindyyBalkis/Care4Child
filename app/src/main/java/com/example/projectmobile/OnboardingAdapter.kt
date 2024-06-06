package com.example.projectmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(private val activity: FragmentActivity, private val layouts: List<Int>) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    class OnboardingViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        // No binding necessary since the layouts are static
    }

    override fun getItemViewType(position: Int): Int {
        return layouts[position]
    }

    override fun getItemCount(): Int {
        return layouts.size
    }
}