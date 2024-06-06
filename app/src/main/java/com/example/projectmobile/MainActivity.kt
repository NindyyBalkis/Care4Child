package com.example.projectmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start OnboardingActivity
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)

        // Close MainActivity
        finish()
    }
}