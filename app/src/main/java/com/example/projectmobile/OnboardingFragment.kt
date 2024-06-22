package com.example.projectmobile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
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
        activity?.window?.statusBarColor = activity?.getColor(R.color.white)!!

        val view = inflater.inflate(layoutResId!!, container, false)


        if (layoutResId == R.layout.activity_onboarding3) {
            view.findViewById<AppCompatButton>(R.id.btn_start).setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        // Inflate the layout for this fragment
        return view
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
