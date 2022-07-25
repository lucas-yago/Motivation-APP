package com.lucasyago.motivation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lucasyago.motivation.R
import com.lucasyago.motivation.data.Mock
import com.lucasyago.motivation.databinding.ActivityMainBinding
import com.lucasyago.motivation.infra.MotivationConstants
import com.lucasyago.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setListeners()
        handleFilter(R.id.image_all)
        nextPhrase()
    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    private fun setListeners() {
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun handleUserName() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $userName"
    }

    override fun onClick(view: View) {
        val listId = listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)

        when (view.id) {
            R.id.button_newPhrase -> {
                nextPhrase()
            }
            in listId -> {
                handleFilter(view.id)
            }
            R.id.text_userName -> {
                startActivity(Intent(this, UserActivity::class.java))
            }
        }
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun nextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }
}