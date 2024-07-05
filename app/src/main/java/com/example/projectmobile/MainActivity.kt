package com.example.projectmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobile.data.User
import com.example.projectmobile.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(R.color.softpink)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        val sharedPref = getSharedPreferences("user", MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Check if user has logged in
        auth.currentUser?.let {
            db.reference.child("users").child(it.uid).get().addOnSuccessListener { snapshot ->
                // Set data
                val user = snapshot.getValue(User::class.java)

                editor.putString("id", user?.id)
                editor.putString("nama_lengkap", user?.nama)
                editor.putString("email", user?.email)
                editor.putString("role", user?.role)

                editor.apply()

                // Start HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

                // Close MainActivity
                finish()
            }
        } ?: run {
            // Start OnboardingActivity
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)

            // Close MainActivity
            finish()
        }
    }
}