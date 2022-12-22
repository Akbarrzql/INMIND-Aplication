package com.example.inmind.registerlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.inmind.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()
        btn_reset.setOnClickListener {
            val email = et_email.text.toString()
            if (email.isEmpty()){
                et_email.error = "Masukkan email anda terlebih dahulu"
                et_email.requestFocus()
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        et_email.setText("")
                        et_email.requestFocus()
                        Toast.makeText(this, "Silahkan cek email anda", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
                    et_email.requestFocus()
                }
        }

        logo_out.setOnClickListener {
            val intent = Intent(this, MainRegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}