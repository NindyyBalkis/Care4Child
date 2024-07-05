package com.example.projectmobile.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.databinding.FragmentKontenBinding
import com.example.projectmobile.home.ui.konten.artikel.ArtikelFragment
import com.example.projectmobile.home.ui.konten.modul.ModulFragment
import com.example.projectmobile.home.ui.konten.video.VideoFragment

class KontenFragment : Fragment() {
    private lateinit var binding: FragmentKontenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_konten, container, false)
        binding = FragmentKontenBinding.bind(view)

        swapFragment(ArtikelFragment(), binding.btnArtikelText)

        binding.btnArtikel.setOnClickListener {
            swapFragment(ArtikelFragment(), binding.btnArtikelText)
        }

        binding.btnModul.setOnClickListener {
            swapFragment(ModulFragment(), binding.btnModulText)
        }

        binding.btnVideo.setOnClickListener {
            swapFragment(VideoFragment(), binding.btnVideoText)
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.fragment_container_view, HomeFragment())
                    commit()
                }
        }

        return binding.root
    }

    private fun swapFragment(fragment: Fragment, view: TextView?) {
        binding.btnVideoText.isSelected = false
        binding.btnModulText.isSelected = false
        binding.btnArtikelText.isSelected = false

        binding.btnVideoText.setTextColor(context!!.getColor(R.color.softpink))
        binding.btnModulText.setTextColor(context!!.getColor(R.color.softpink))
        binding.btnArtikelText.setTextColor(context!!.getColor(R.color.softpink))

        view?.isSelected = true
        view?.setTextColor(context!!.getColor(R.color.white))

        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_konten_container, fragment)
            commit()
        }
    }
}