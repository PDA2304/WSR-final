package com.example.wsr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SignInScreen::class.java)
        startActivity(intent)
        Thread.sleep(1000)
        finish()
    }
}