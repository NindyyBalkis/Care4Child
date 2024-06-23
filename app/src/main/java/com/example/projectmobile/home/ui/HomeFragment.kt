package com.example.projectmobile.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.cvKontenList.setOnClickListener {
            swapFragment(KontenFragment())
        }

        binding.cvKontenMenu.setOnClickListener {
            swapFragment(KontenFragment())
        }

        return binding.root
    }

    private fun swapFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container_view, fragment)
            ?.commit()
    }
}