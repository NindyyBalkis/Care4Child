package com.example.projectmobile.home.ui.konten.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.dummyArtikelList
import com.example.projectmobile.databinding.FragmentArtikelBinding

class ArtikelFragment : Fragment() {
    private lateinit var binding: FragmentArtikelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artikel, container, false)
        binding = FragmentArtikelBinding.bind(view)

        binding.rvArtikel.apply {
            val adapter = ArtikelRecyclerAdapter()
            adapter.addData(dummyArtikelList)
            this.adapter = adapter
        }

        return binding.root
    }
}