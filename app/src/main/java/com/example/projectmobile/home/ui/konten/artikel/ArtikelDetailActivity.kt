package com.example.projectmobile.home.ui.konten.artikel

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.R
import com.example.projectmobile.data.dummyArtikelList
import com.example.projectmobile.databinding.ActivityArtikelDetailBinding

class ArtikelDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtikelDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityArtikelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            OnBackPressedDispatcher().onBackPressed()
        }

        val id = intent.getStringExtra("id")
        val artikel = dummyArtikelList.find { it.id == id }

        binding.tvIsi.text = artikel?.isi
        binding.tvJudul.text = artikel?.judul

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}