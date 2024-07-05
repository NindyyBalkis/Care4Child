package com.example.projectmobile.home.ui.konten.video

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.R
import com.example.projectmobile.data.dummyVideoList
import com.example.projectmobile.databinding.ActivityVideoDetailBinding

class VideoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVideoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            OnBackPressedDispatcher().onBackPressed()
        }

        val id = intent.getStringExtra("id")
        val video = dummyVideoList.find { it.id == id }

        binding.tvIsi.text = video?.isi
        binding.tvJudul.text = video?.judul

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}