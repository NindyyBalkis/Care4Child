package com.example.projectmobile.home.ui.konten.video

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.R
import com.example.projectmobile.data.Video
import com.example.projectmobile.databinding.ActivityVideoDetailBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class VideoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoDetailBinding
    private lateinit var db: FirebaseDatabase
    private lateinit var storage: FirebaseStorage

    private var video: Video? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVideoDetailBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.softpink)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        binding.backButton.setOnClickListener {
            OnBackPressedDispatcher().onBackPressed()
        }

        val id = intent.getStringExtra("id")

        db.getReference("video").child(id!!).get().addOnSuccessListener {
            video = it.getValue(Video::class.java)

            binding.tvIsi.text = video?.isi
            binding.tvJudul.text = video?.judul

            binding.vwVideo.setVideoURI(Uri.parse(video?.video))

            val mediaController = MediaController(this)
            binding.vwVideo.setMediaController(mediaController)
            binding.vwVideo.setZOrderOnTop(true)

            // Start playback
            binding.vwVideo.start()
        }

        val sharedPref = getSharedPreferences("user", MODE_PRIVATE)
        val role = sharedPref.getString("role", "")

        if (role == "admin") {
            binding.btnDelete.visibility = View.VISIBLE
        } else {
            binding.btnDelete.visibility = View.GONE
        }

        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Hapus Video")
                .setMessage("Apakah anda yakin ingin menghapus video ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    storage.getReferenceFromUrl(video?.video!!).delete().addOnCompleteListener {
                        db.getReference("video").child(id).removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(this, "Video berhasil dihapus", Toast.LENGTH_SHORT)
                                    .show()

                                dialog.dismiss()
                                onBackPressed()
                            }
                    }.addOnFailureListener {
                        Log.e("ModulDetailActivity", "Error deleting modul", it)
                    }
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}