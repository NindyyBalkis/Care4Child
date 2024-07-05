package com.example.projectmobile.home.ui

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.Artikel
import com.example.projectmobile.data.Modul
import com.example.projectmobile.data.Video
import com.example.projectmobile.databinding.FragmentKontenBinding
import com.example.projectmobile.home.ui.konten.artikel.ArtikelFragment
import com.example.projectmobile.home.ui.konten.modul.ModulFragment
import com.example.projectmobile.home.ui.konten.video.VideoFragment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class KontenFragment : Fragment() {
    private lateinit var binding: FragmentKontenBinding
    private lateinit var dialogView: View
    private lateinit var db: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_konten, container, false)
        binding = FragmentKontenBinding.bind(view)

        db = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()

        swapFragment(ArtikelFragment(), binding.btnArtikelText)

        binding.btnArtikel.setOnClickListener {
            swapFragment(ArtikelFragment(), binding.btnArtikelText)
        }

        binding.btnModul.setOnClickListener {
            swapFragment(ModulFragment(), binding.btnModulText)
        }

        binding.btnVideo.setOnClickListener {
            swapFragment(VideoFragment(), binding.btnVideoText)
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.fragment_container_view, HomeFragment())
                    commit()
                }
        }

        // Add button if role is admin
        val sharedPref = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val role = sharedPref.getString("role", "user")

        if (role == "admin") {
            binding.addButton.visibility = View.VISIBLE
        } else {
            binding.addButton.visibility = View.GONE
        }

        var file: Uri? = null
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                file = it
                dialogView.findViewById<TextView>(R.id.btn_file).text = "File terpilih"
            }
        }

        binding.addButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            dialogView = layoutInflater.inflate(R.layout.layout_add_dialog, null)

            // Get current fragment
            val currentFragment =
                childFragmentManager.findFragmentById(R.id.fragment_konten_container)

            // Ubah isi
            when (currentFragment) {
                is VideoFragment -> {
                    dialogView.findViewById<Button>(R.id.btn_file).text = "Upload Video"
                    dialogView.findViewById<TextView>(R.id.tv_judul).text = "Tambah Konten Video"
                    dialogView.findViewById<Button>(R.id.btn_file).setOnClickListener {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
                    }
                }

                is ModulFragment -> {
                    dialogView.findViewById<Button>(R.id.btn_file).text = "Upload File"
                    dialogView.findViewById<TextView>(R.id.tv_judul).text = "Tambah Konten Modul"
                    dialogView.findViewById<Button>(R.id.btn_file).setOnClickListener {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                }

                is ArtikelFragment -> {
                    dialogView.findViewById<Button>(R.id.btn_file).text = "Upload File"
                    dialogView.findViewById<TextView>(R.id.tv_judul).text = "Tambah Konten Artikel"
                    dialogView.findViewById<Button>(R.id.btn_file).setOnClickListener {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                }
            }




            dialogView.findViewById<Button>(R.id.btn_upload).setOnClickListener {
                val judul = dialogView.findViewById<EditText>(R.id.et_judul).text
                val isi = dialogView.findViewById<EditText>(R.id.et_isi).text

                when (currentFragment) {
                    is VideoFragment -> {
                        val id = db.getReference("video").push().key.toString()

                        val ref = storage.getReference("video").child("$id.mp4")
                        val uploadTask = ref.putFile(file!!)

                        uploadTask.addOnSuccessListener {
                            ref.downloadUrl.addOnSuccessListener {
                                val video = Video(
                                    id = db.getReference("video").push().key.toString(),
                                    judul = judul.toString(),
                                    isi = isi.toString(),
                                    video = it.toString()
                                )
                                db.getReference("video").child(video.id).setValue(video)

                                Toast.makeText(
                                    requireContext(),
                                    "Konten video berhasil ditambahkan",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                alertDialog.dismiss()
                            }
                        }
                    }

                    // Simpan modul
                    is ModulFragment -> {
                        val id = db.getReference("modul").push().key.toString()

                        val ref = storage.getReference("modul").child("$id.jpg")
                        val uploadTask = ref.putFile(file!!)

                        uploadTask.addOnSuccessListener {
                            ref.downloadUrl.addOnSuccessListener {
                                val modul = Modul(
                                    id = db.getReference("modul").push().key.toString(),
                                    judul = judul.toString(),
                                    isi = isi.toString(),
                                    gambar = it.toString()
                                )
                                db.getReference("modul").child(modul.id).setValue(modul)

                                Toast.makeText(
                                    requireContext(),
                                    "Konten modul berhasil ditambahkan",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                alertDialog.dismiss()
                            }
                        }
                    }

                    is ArtikelFragment -> {
                        val id = db.getReference("artikel").push().key.toString()

                        val ref = storage.getReference("artikel").child("$id.jpg")
                        val uploadTask = ref.putFile(file!!)

                        uploadTask.addOnSuccessListener {
                            ref.downloadUrl.addOnSuccessListener {
                                val artikel = Artikel(
                                    id = db.getReference("artikel").push().key.toString(),
                                    judul = judul.toString(),
                                    isi = isi.toString(),
                                    gambar = it.toString()
                                )
                                db.getReference("artikel").child(artikel.id).setValue(artikel)

                                Toast.makeText(
                                    requireContext(),
                                    "Konten artikel berhasil ditambahkan",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                alertDialog.dismiss()
                            }
                        }
                    }
                }
            }

            builder.setView(dialogView)

            alertDialog = builder.create()
            alertDialog.show()
        }

        return binding.root
    }

    private fun swapFragment(fragment: Fragment, view: TextView?) {
        binding.btnVideoText.isSelected = false
        binding.btnModulText.isSelected = false
        binding.btnArtikelText.isSelected = false

        binding.btnVideoText.setTextColor(requireContext().getColor(R.color.softpink))
        binding.btnModulText.setTextColor(requireContext().getColor(R.color.softpink))
        binding.btnArtikelText.setTextColor(requireContext().getColor(R.color.softpink))

        view?.isSelected = true
        view?.setTextColor(requireContext().getColor(R.color.white))

        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_konten_container, fragment)
            commit()
        }
    }
}