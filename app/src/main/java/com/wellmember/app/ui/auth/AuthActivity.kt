package com.wellmember.app.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData

import com.wellmember.app.R
import com.wellmember.app.TestActivity
import com.wellmember.app.data.UserPreferences
import com.wellmember.app.startNewActivity

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {

            val activity = if (it == null) AuthActivity::class.java else TestActivity::class.java
            startNewActivity(activity)

        })

    }

}