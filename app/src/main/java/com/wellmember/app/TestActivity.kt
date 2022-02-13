package com.wellmember.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*

import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.wellmember.app.data.UserPreferences
import com.wellmember.app.databinding.ActivityTestBinding
import com.wellmember.app.ui.auth.AuthActivity

class TestActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // profile clicker
        val headView = binding.navView.getHeaderView(0)
        val imageProfile = headView.findViewById<ImageView>(R.id.imageView)
        val nameProfile = headView.findViewById<TextView>(R.id.nameRedirect)
        val customerIdProfile = headView.findViewById<TextView>(R.id.customerIdRedirect)

        imageProfile.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        nameProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        customerIdProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {

            if (it == null){
                Handler().postDelayed({
                    startNewActivity(AuthActivity::class.java)
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_out);
                    finish()
                }, 500)
            }

        })

        val logoutBtn: Button = binding.logoutBtn

        // btn can remove
        logoutBtn.setOnClickListener{

            Toast.makeText(this, "logout", Toast.LENGTH_LONG).show()

        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_archive, R.id.nav_bonus, R.id.nav_mall,
                R.id.nav_activity_sign_in, R.id.nav_order_record, R.id.nav_product_testimonials,
                R.id.nav_form, R.id.nav_question
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}