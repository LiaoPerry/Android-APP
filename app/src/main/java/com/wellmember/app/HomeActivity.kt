package com.wellmember.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.wellmember.app.data.UserPreferences

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            //Toast.makeText(this, it ?: "Token is null", Toast.LENGTH_LONG).show()
            //startActivity(Intent(this, AuthActivity::class.java))
        })



//        if (){
//            startActivity(Intent(this, AuthActivity::class.java))
//        } else {
            Handler().postDelayed({
            startActivity(Intent(this, TestActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish()
        }, 2000)
//
//        }

//        startActivity(Intent(this, AuthActivity::class.java))
//

    }
}