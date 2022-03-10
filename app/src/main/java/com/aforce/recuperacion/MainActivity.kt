package com.aforce.recuperacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.aforce.recuperacion.databinding.ActivityMainBinding
import com.aforce.recuperacion.db.DatabaseProduct


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationC = findNavController(R.id.container)
        binding.bottomNavigation.setupWithNavController(navigationC)



    }
}
