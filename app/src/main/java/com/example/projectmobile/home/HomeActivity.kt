package com.example.projectmobile.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.projectmobile.R
import com.example.projectmobile.databinding.ActivityHomeBinding
import com.example.projectmobile.home.ui.HomeFragment
import com.example.projectmobile.home.ui.KontenFragment
import com.example.projectmobile.home.ui.ProfileFragment
import com.example.projectmobile.home.ui.TanyaAhliFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.softpink)
        setContentView(binding.root)


        binding.bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    swapFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_tanya_ahli -> {
                    swapFragment(TanyaAhliFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_konten -> {
                    swapFragment(KontenFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_profil -> {
                    swapFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}
