package com.example.projectmobile.home.ui.konten.artikel

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmobile.R
import com.example.projectmobile.data.Artikel
import com.example.projectmobile.databinding.LayoutArtikelItemBinding

class ArtikelRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Artikel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutArtikelItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArtikelBinding(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArtikelBinding -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    holder.onClick(items[position])
                }
            }
        }
    }

    fun addData(artikelList: List<Artikel>) {
        items = artikelList
        notifyDataSetChanged()
    }

    class ArtikelBinding(val binding: LayoutArtikelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artikel: Artikel) {
            binding.tvJudul.text = artikel.judul
            binding.tvIsi.text = artikel.isi

            Glide.with(this.itemView)
                .load(artikel.gambar)
                .placeholder(R.color.pink)
                .into(binding.ivGambar)

        }

        fun onClick(article: Artikel) {
            val intent = Intent(binding.root.context, ArtikelDetailActivity::class.java)

            intent.putExtra("id", article.id)

            binding.root.context.startActivity(intent)
        }
    }
}