package com.lucasyago.motivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucasyago.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}