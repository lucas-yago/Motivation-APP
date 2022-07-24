package com.lucasyago.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucasyago.motivation.infra.MotivationConstants
import com.lucasyago.motivation.R
import com.lucasyago.motivation.infra.SecurityPreferences
import com.lucasyago.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.buttonSave.setOnClickListener(this)
        verifyUserName()

    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()

        SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}