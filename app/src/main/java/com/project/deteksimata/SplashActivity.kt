package com.project.deteksimata

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logo)

        // Create fade-in animation for logo
        val fadeIn = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 1500 // Animation lasts for 1.5 seconds
            fillAfter = true // Keeps the logo visible after animation ends
        }

        // Start the animation
        logo.startAnimation(fadeIn)

        // Set a delay of 3 seconds before moving to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish() // Close SplashActivity
        }, 3000) // 3 seconds delay
    }
}
