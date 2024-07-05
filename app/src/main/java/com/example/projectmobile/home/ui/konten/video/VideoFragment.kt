package com.example.projectmobile.home.ui.konten.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.Video
import com.example.projectmobile.databinding.FragmentVideoBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var db: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        binding = FragmentVideoBinding.bind(view)

        binding.rvVideo.apply {
            val adapter = VideoRecyclerAdapter()
            this.adapter = adapter
        }

        var videoList: ArrayList<Video> = ArrayList()
        db = FirebaseDatabase.getInstance()

        db.getReference("video").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    videoList.clear()
                    for (data in snapshot.children) {
                        val video = data.getValue(Video::class.java)
                        videoList.add(video!!)
                    }

                    (binding.rvVideo.adapter as VideoRecyclerAdapter).addData(videoList)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
        )

        return binding.root
    }
}