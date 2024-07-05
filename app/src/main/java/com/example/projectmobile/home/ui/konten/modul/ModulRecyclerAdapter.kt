package com.example.projectmobile.home.ui.konten.modul

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmobile.R
import com.example.projectmobile.data.Modul
import com.example.projectmobile.databinding.LayoutModulItemBinding

class ModulRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Modul> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutModulItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ModulBinding(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ModulBinding -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    holder.onClick(items[position])
                }
            }
        }
    }

    fun addData(modulList: List<Modul>) {
        items = modulList
        notifyDataSetChanged()
    }

    class ModulBinding(val binding: LayoutModulItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modul: Modul) {
            binding.tvJudul.text = modul.judul
            binding.tvIsi.text = modul.isi

            Glide.with(this.itemView)
                .load(modul.gambar)
                .placeholder(R.color.pink)
                .into(binding.ivGambar)

        }

        fun onClick(modul: Modul) {
            val intent = Intent(binding.root.context, ModulDetailActivity::class.java)

            intent.putExtra("id", modul.id)

            binding.root.context.startActivity(intent)
        }
    }
}