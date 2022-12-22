package com.example.inmind.profile

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inmind.R
import com.example.inmind.databinding.ActivityProfileBinding
import com.example.inmind.registerlogin.MainRegisterActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progress : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        checkUser()
        onClickUser()
        displayUser()
    }

    private fun displayUser() {
        val email = auth.currentUser?.email
        tv_email.text = email
    }

    private fun onClickUser(){
        btn_logout.setOnClickListener {
            auth.signOut()
            checkUser()
            googleSignOut()
        }
    }

    private fun checkUser(){
        auth = FirebaseAuth.getInstance()
        val firebaseUser = auth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
            tv_email.text = email
        }
        else{
            startActivity(Intent(this, MainRegisterActivity::class.java))
            finish()
        }
    }

    private fun googleSignOut(){
        auth.signOut()
        startActivity(Intent(this, MainRegisterActivity::class.java))
    }
}