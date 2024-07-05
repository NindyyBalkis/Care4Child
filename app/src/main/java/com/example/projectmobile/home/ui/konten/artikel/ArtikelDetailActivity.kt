package com.example.projectmobile.home.ui.konten.artikel

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.projectmobile.R
import com.example.projectmobile.data.Artikel
import com.example.projectmobile.databinding.ActivityArtikelDetailBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ArtikelDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtikelDetailBinding
    private lateinit var db: FirebaseDatabase
    private lateinit var storage: FirebaseStorage

    private var artikel: Artikel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityArtikelDetailBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.softpink)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        val id = intent.getStringExtra("id")

        db.getReference("artikel").child(id!!).get().addOnSuccessListener {
            artikel = it.getValue(Artikel::class.java)

            binding.tvIsi.text = artikel?.isi
            binding.tvJudul.text = artikel?.judul

            Glide.with(this)
                .load(artikel?.gambar)
                .placeholder(R.color.disabled)
                .into(binding.ivGambar)
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
                .setTitle("Hapus Artikel")
                .setMessage("Apakah anda yakin ingin menghapus artikel ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    storage.getReferenceFromUrl(artikel?.gambar!!).delete().addOnCompleteListener {
                        db.getReference("artikel").child(id).removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(this, "Artikel berhasil dihapus", Toast.LENGTH_SHORT)
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}