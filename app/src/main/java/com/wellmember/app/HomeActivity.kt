package com.wellmember.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.wellmember.app.Data.UserPreferences
import com.wellmember.app.UI.Auth.AuthActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            Toast.makeText(this, it ?: "Token is null", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
        })

        finish()
//          startActivity(Intent(this, AuthActivity::class.java))

    }
}