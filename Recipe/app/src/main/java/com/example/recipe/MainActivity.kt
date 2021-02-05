package com.example.recipe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    //Variables
    var topAnim: Animation? = null
    var bottomAnim: Animation? = null
    var papers: ImageView? = null
    var logo: ImageView? = null
    var slogan: TextView? = null
    var lines: View? = null
    var splash_screen: Long = 5000L


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
//            WindowInsetsController.hide(Int), WindowInsets.Type.statusBars(),
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        //Hooks
        papers = findViewById<ImageView>(R.id.recipes_img)
        logo = findViewById<ImageView>(R.id.logo_img)
        slogan = findViewById(R.id.tagline_text)
        lines = findViewById(R.id.divider)

        papers!!.setAnimation(topAnim)
        logo!!.setAnimation(bottomAnim)
        lines!!.setAnimation(bottomAnim)
        slogan!!.setAnimation(bottomAnim)


        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@MainActivity, MainRecipe::class.java)
            startActivity(intent)
            finish()
        }, splash_screen)


    }

}
