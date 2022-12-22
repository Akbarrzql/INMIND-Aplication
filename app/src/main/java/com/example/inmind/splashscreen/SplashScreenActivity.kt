package com.example.inmind.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inmind.R
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        splash_screen_logo.alpha = 0f
        splash_screen_logo.animate().setDuration(4000).alpha(1f).withEndAction {
            val i = android.content.Intent(this, com.example.inmind.registerlogin.MainRegisterActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}