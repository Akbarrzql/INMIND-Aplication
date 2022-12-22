package com.example.inmind.registerlogin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.inmind.MainActivity
import com.example.inmind.R
import com.example.inmind.databinding.ActivityRegisterBinding
import com.example.inmind.profile.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progress: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private var nama = ""
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        //function
        onClickUser()
        register()
    }

    private fun register() {
        progress = ProgressDialog(this)
        progress.setTitle("Please wait")
        progress.setMessage("Registering user")
        progress.setCanceledOnTouchOutside(false)

        auth = FirebaseAuth.getInstance()
    }

    private fun onClickUser(){
        logo_out.setOnClickListener {
            val intent = Intent(this, MainRegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        btn_buat_akun.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        nama = txt_nama.text.toString()
        email = txt_email.text.toString()
        password = txt_password.text.toString()

        if (nama.isEmpty()) {
            txt_nama.error = "Nama tidak boleh kosong"
        } else if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_email.error = "Email tidak boleh kosong dan harus valid"
        } else if (password.isEmpty()) {
            txt_password.error = "Password tidak boleh kosong"
        }else if (password.length < 6) {
            txt_password.error = "Password harus lebih dari 6 karakter"
        } else {
            firebaseRegister()
        }
    }

    private fun firebaseRegister() {
        progress.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progress.dismiss()
                val firebaseUser = auth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Akun $email berhasil dibuat", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progress.dismiss()
                Toast.makeText(this, "Gagal membuat akun karena ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}