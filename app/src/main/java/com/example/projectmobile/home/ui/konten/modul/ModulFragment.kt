package com.example.projectmobile.home.ui.konten.modul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.data.Modul
import com.example.projectmobile.databinding.FragmentModulBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ModulFragment : Fragment() {
    private lateinit var binding: FragmentModulBinding
    private lateinit var db: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_modul, container, false)
        binding = FragmentModulBinding.bind(view)

        binding.rvModul.apply {
            val adapter = ModulRecyclerAdapter()
            this.adapter = adapter
        }

        var modulList: ArrayList<Modul> = ArrayList()
        db = FirebaseDatabase.getInstance()

        db.getReference("modul").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    modulList.clear()
                    for (data in snapshot.children) {
                        val modul = data.getValue(Modul::class.java)
                        modulList.add(modul!!)
                    }

                    (binding.rvModul.adapter as ModulRecyclerAdapter).addData(modulList)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
        )

        return binding.root
    }

}