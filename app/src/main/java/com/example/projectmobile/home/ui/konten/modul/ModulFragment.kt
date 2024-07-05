package com.example.projectmobile.home.ui.konten.modul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.dummyModulList
import com.example.projectmobile.databinding.FragmentModulBinding

class ModulFragment : Fragment() {
    private lateinit var binding: FragmentModulBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_modul, container, false)
        binding = FragmentModulBinding.bind(view)

        binding.rvModul.apply {
            val adapter = ModulRecyclerAdapter()
            adapter.addData(dummyModulList)
            this.adapter = adapter
        }

        return binding.root
    }

}