package com.example.projectmobile.home.ui.konten.video

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobile.data.Video
import com.example.projectmobile.databinding.LayoutVideoItemBinding

class VideoRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Video> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutVideoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoBinding(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VideoBinding -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    holder.onClick(items[position])
                }
            }
        }
    }

    fun addData(videoList: List<Video>) {
        items = videoList
        notifyDataSetChanged()
    }

    class VideoBinding(val binding: LayoutVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            binding.tvJudul.text = video.judul
            binding.tvIsi.text = video.isi
        }

        fun onClick(video: Video) {
            val intent = Intent(binding.root.context, VideoDetailActivity::class.java)

            intent.putExtra("id", video.id)

            binding.root.context.startActivity(intent)
        }
    }
}