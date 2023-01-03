package com.example.inmind.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.inmind.R
import com.example.inmind.registerlogin.MainRegisterActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var progress : ProgressDialog
    private lateinit var tv_email : TextView
    private lateinit var btn_logout : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        auth = FirebaseAuth.getInstance()
        tv_email = view.findViewById(R.id.tv_email)
        btn_logout = view.findViewById(R.id.btn_logout)

        checkUser()
        onClickUser()
        displayUser()

        return view
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
            startActivity(Intent(activity, MainRegisterActivity::class.java))
            activity?.finish()
        }
    }

    private fun googleSignOut(){
        auth.signOut()
        startActivity(Intent(activity, MainRegisterActivity::class.java))
    }

}