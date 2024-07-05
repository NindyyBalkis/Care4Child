package com.example.projectmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile.data.User
import com.example.projectmobile.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.softpink)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

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
                auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Create user with the NamaLengkap
                            val uid = auth.currentUser?.uid

                            val user = User(
                                id = uid.toString(),
                                nama = nama_lengkap.toString(),
                                email = email.toString(),
                            )

                            db.getReference("users").child(uid.toString()).setValue(user)

                            Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {

                            Log.d("RegisterActivity", "Registrasi gagal", it.exception)

                            Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
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