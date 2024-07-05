package com.example.projectmobile.home.ui.konten.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.dummyVideoList
import com.example.projectmobile.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        binding = FragmentVideoBinding.bind(view)

        binding.rvVideo.apply {
            val adapter = VideoRecyclerAdapter()
            adapter.addData(dummyVideoList)
            this.adapter = adapter
        }

        return binding.root
    }
}