package com.example.dogedex.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogedex.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        TODO("Not yet implemented")
    }
}