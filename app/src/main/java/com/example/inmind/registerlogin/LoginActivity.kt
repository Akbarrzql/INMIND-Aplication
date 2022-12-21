package com.example.inmind.registerlogin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.inmind.R
import com.example.inmind.databinding.ActivityLoginBinding
import com.example.inmind.profile.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_login
import kotlinx.android.synthetic.main.activity_login.logo_out

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var progress: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        onClickUser()
        login()
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

        btn_masuk_akun.setOnClickListener {
            validateData()
        }

        tv_forgot_password.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }


    private fun validateData() {
        email = txt_email_login.text.toString()
        password = txt_password_login.text.toString()

        if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_email_login.error = "Email tidak boleh kosong dan harus valid"
        } else if (password.isEmpty()) {
            txt_password_login.error = "Password tidak boleh kosong"
        } else {
            firebaselogin()
        }
    }

    private fun firebaselogin() {
        progress.show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
            progress.dismiss()
            val firebaseUser = auth.currentUser
            val email = firebaseUser!!.email
            Toast.makeText(this, "Login berhasil dengan email $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            .addOnFailureListener {
                progress.dismiss()
                Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show()
            }
    }

    private fun login(){
        progress = ProgressDialog(this)
        progress.setTitle("Please wait")
        progress.setMessage("Logging in...")
        progress.setCanceledOnTouchOutside(false)

        //init firebase auth
        auth = FirebaseAuth.getInstance()
        checkUser()
    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }
}