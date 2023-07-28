package com.mikerusetsky.appcinema.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikerusetsky.appcinema.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var binding7 : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding7 = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding7.root)

        binding7.splashView.alpha = 0f
        binding7.splashView.animate().setDuration(3000).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}