package com.example.recipe
      

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        R.id.action_aboutFragment_to_home
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.aboutFragment, R.id.home, R.id.login_SignUp, R.id.list, R.id.detail_view))
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Set Status Bar Color & transparency
        val window: Window = this@MainActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) // deprecated
        // Window.setStatusBarColor(R.color.green) // for API 30+
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.top_bar)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("ABOUT", destination.id.toString())
            //id.toString())
            if (destination.id == R.id.action_aboutFragment_to_home) Log.d("ABOUT", "Go Home")
            /*findNavController().navigate(
                                    R.id.nav_host_fragment_container
                                    //bundleOf(Pair("toolbar_title", "My Details Fragment Title"))
                                )*/

            /* R.id.navigation_home -> "My title"
                R.id.navigation_task_start -> "My title2"
                R.id.navigation_task_finish -> "My title3"
                R.id.navigation_status -> "My title3"
                R.id.navigation_settings -> "My title4"
                else -> "Default title"*/
        //}

        }
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.


    }

}
