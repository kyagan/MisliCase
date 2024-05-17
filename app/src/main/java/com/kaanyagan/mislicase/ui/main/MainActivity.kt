package com.kaanyagan.mislicase.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaanyagan.mislicase.R
import com.kaanyagan.mislicase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}