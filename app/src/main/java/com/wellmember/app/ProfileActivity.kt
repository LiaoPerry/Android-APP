package com.wellmember.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // init
        val backBtn = findViewById<ImageButton>(R.id.back_arrow)
        val profileBtn = findViewById<Button>(R.id.profileBtn)
        val vipProfileBtn = findViewById<Button>(R.id.vipProfileBtn)
        val mallLoginBtn = findViewById<Button>(R.id.mallLoginBtn)
        val modifyPasswordBtn = findViewById<Button>(R.id.modifyPasswordBtn)

        // back arrow
        backBtn.setOnClickListener {
            finish()
        }

        // TODO 四個頁面
        profileBtn.setOnClickListener {
            Toast.makeText(this, "profile", Toast.LENGTH_LONG).show()
        }
        vipProfileBtn.setOnClickListener {
            Toast.makeText(this, "vipProfile", Toast.LENGTH_LONG).show()
        }
        mallLoginBtn.setOnClickListener {
            Toast.makeText(this, "mall", Toast.LENGTH_LONG).show()
        }
        modifyPasswordBtn.setOnClickListener {
            Toast.makeText(this, "modifyPassword", Toast.LENGTH_LONG).show()
        }



    }
}