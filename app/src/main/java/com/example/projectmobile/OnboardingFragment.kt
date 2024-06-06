package com.example.projectmobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OnboardingFragment : Fragment() {
    private var layoutResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutResId = it.getInt(ARG_LAYOUT_RES_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutResId!!, container, false)
    }

    companion object {
        private const val ARG_LAYOUT_RES_ID = "layout_res_id"

        @JvmStatic
        fun newInstance(layoutResId: Int) =
            OnboardingFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_LAYOUT_RES_ID, layoutResId)
                }
            }
    }
}
