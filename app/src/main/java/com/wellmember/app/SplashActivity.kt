package com.wellmember.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wellmember.app.Auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)

        // splash animation
        Handler().postDelayed({
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }, 2000)
    }

}