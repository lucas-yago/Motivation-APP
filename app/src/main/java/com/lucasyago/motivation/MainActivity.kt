package com.lucasyago.motivation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucasyago.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonNewPhrase.setOnClickListener(this)

        handleUserName()
    }

    @SuppressLint("SetTextI18n")
    private fun handleUserName() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá $userName"
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_newPhrase) {
            newPhrase()
        }
    }

    private fun newPhrase() {
        Toast.makeText(this, "voce solicitou uma nova frase", Toast.LENGTH_SHORT).show()
    }
}