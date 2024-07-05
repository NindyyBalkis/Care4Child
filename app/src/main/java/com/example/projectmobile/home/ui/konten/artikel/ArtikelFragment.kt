package com.example.projectmobile.home.ui.konten.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.Artikel
import com.example.projectmobile.databinding.FragmentArtikelBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ArtikelFragment : Fragment() {
    private lateinit var binding: FragmentArtikelBinding
    private lateinit var db: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artikel, container, false)
        binding = FragmentArtikelBinding.bind(view)

        binding.rvArtikel.apply {
            val adapter = ArtikelRecyclerAdapter()
            this.adapter = adapter
        }

        var artikelList: ArrayList<Artikel> = ArrayList()
        db = FirebaseDatabase.getInstance()

        db.getReference("artikel").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    artikelList.clear()
                    for (data in snapshot.children) {
                        val modul = data.getValue(Artikel::class.java)
                        artikelList.add(modul!!)
                    }

                    (binding.rvArtikel.adapter as ArtikelRecyclerAdapter).addData(artikelList)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
        )

        return binding.root
    }
}