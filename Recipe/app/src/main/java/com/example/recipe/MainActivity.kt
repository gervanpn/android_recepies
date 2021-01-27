package com.example.recipe
      

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.home2, R.id.login_SignUp, R.id.list, R.id.detail_view))
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Initialize Firebase Auth

        //var currentUser = mAuth!!.currentUser
        //setupUI()
        //configureGoogleSignIn()



    }










    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.


    }



}
