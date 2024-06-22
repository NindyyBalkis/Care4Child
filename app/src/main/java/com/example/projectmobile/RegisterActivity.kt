package com.example.projectmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.pink)

        binding.btnDaftar.setOnClickListener {
            val nama_lengkap = binding.etNamaLengkap.text
            val email = binding.etEmail.text
            val password = binding.etPassword.text
            val confirm_password = binding.etConfirmPassword.text
            val setuju = binding.cbSetuju.isChecked

            if (!setuju) {
                Toast.makeText(
                    this,
                    "Anda harus menyetujui syarat dan ketentuan",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (nama_lengkap.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
                binding.etNamaLengkap.error = "Nama Lengkap tidak boleh kosong"
                binding.etEmail.error = "Email tidak boleh kosong"
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etConfirmPassword.error = "Konfirmasi Password tidak boleh kosong"

                Toast.makeText(this, "Lengkapi semua input", Toast.LENGTH_SHORT).show()
            } else if (password.toString() != confirm_password.toString()) {
                binding.etPassword.error = "Password tidak sama"
                binding.etConfirmPassword.error = "Password tidak sama"

                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
            } else {
                // Start MainActivity

                Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show()

            }

        }

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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