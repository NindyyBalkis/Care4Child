package com.example.projectmobile.home.ui.konten.modul

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
import com.example.projectmobile.data.Modul
import com.example.projectmobile.databinding.ActivityModulDetailBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ModulDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModulDetailBinding
    private lateinit var db: FirebaseDatabase
    private lateinit var storage: FirebaseStorage

    private var modul: Modul? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityModulDetailBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.softpink)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        val id = intent.getStringExtra("id")

        db.getReference("modul").child(id!!).get().addOnSuccessListener {
            modul = it.getValue(Modul::class.java)

            binding.tvIsi.text = modul?.isi
            binding.tvJudul.text = modul?.judul

            Glide.with(this)
                .load(modul?.gambar)
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
                .setTitle("Hapus Modul")
                .setMessage("Apakah anda yakin ingin menghapus modul ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    storage.getReferenceFromUrl(modul?.gambar!!).delete().addOnCompleteListener {
                        db.getReference("modul").child(id).removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(this, "Modul berhasil dihapus", Toast.LENGTH_SHORT)
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