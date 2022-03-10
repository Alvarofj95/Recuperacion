package com.aforce.recuperacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.aforce.recuperacion.databinding.ActivityMainBinding
import com.aforce.recuperacion.db.DatabaseProduct


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var db: DatabaseProduct
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room
            .databaseBuilder(applicationContext, DatabaseProduct::class.java, "product.db")
            .allowMainThreadQueries()
            .build()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

val Fragment.db: DatabaseProduct
    get() = (requireActivity() as MainActivity).db