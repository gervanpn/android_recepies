package com.example.recipe

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainRecipe : AppCompatActivity() {

    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("RestrictedApi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recipe)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.login_SignUp,
                R.id.list,
                R.id.detail_view
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        // Set Status Bar Color & transparency
        val window: Window = this@MainRecipe.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@MainRecipe, R.color.top_bar)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}