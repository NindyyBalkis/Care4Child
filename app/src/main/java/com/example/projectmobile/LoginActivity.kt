package com.example.projectmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.databinding.ActivityLoginBinding
import com.example.projectmobile.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.pink)

        binding.btnMasuk.setOnClickListener {
            val email = binding.etEmail.text
            val password = binding.etPassword.text

            if (email.isEmpty() || password.isEmpty()) {
                binding.etEmail.error = "Email tidak boleh kosong"
                binding.etPassword.error = "Password tidak boleh kosong"

                Toast.makeText(this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Start MainActivity
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.tvSignUp.setOnClickListener {
            // Start RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}