package com.example.projectmobile.home.ui.konten.modul

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.R
import com.example.projectmobile.data.dummyModulList
import com.example.projectmobile.databinding.ActivityModulDetailBinding

class ModulDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModulDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityModulDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            OnBackPressedDispatcher().onBackPressed()
        }

        val id = intent.getStringExtra("id")
        val modul = dummyModulList.find { it.id == id }

        binding.tvIsi.text = modul?.isi
        binding.tvJudul.text = modul?.judul

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}