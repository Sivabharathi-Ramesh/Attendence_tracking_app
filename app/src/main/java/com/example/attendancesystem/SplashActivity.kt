package com.example.attendancesystem

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.attendancesystem.R

// SuppressLint is needed for Handler().postDelayed which is fine for a simple splash
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    // Time in milliseconds that the splash screen will be shown (e.g., 1500 = 1.5 seconds)
    private val SPLASH_DISPLAY_LENGTH: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Use a Handler to delay the execution of the navigation
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent that will start the LoginActivity
            val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(mainIntent)

            // Close this activity so the user can't navigate back to it
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
