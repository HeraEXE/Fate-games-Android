package com.hera.fate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hera.fate.R
import com.hera.fate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Defining binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up Toolbar.
        setSupportActionBar(binding.toolbar)

        // Setting up Bottom Navigation.
        val fragment: FragmentContainerView = findViewById(R.id.fragment)
        binding.bottomNavigation.setupWithNavController(fragment.findNavController())
    }
}