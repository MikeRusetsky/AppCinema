package com.mikerusetsky.appcinema.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikerusetsky.appcinema.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    private var binding7: ActivitySplashBinding? = null
    private val binding: ActivitySplashBinding
        get() = binding7!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding7 = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Использование карутин для второго активити
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}