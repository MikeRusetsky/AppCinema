package com.mikerusetsky.appcinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import com.google.android.material.navigation.NavigationBarMenu
import com.mikerusetsky.appcinema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initNavigation()
    }

        private fun initNavigation() {
            Toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.settings -> {
                        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            Navigation_Bottom.setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.favorites -> {
                        Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.watch_later -> {
                        Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.collections -> {
                        Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }
    }