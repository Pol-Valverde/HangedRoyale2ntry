package com.example.hangedroyale2ntry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangedroyale2ntry.databinding.ActivityMainMenuBinding
import com.example.hangedroyale2ntry.databinding.ActivityRegisterBinding

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener{
            val intent = Intent(this@MainMenuActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.exitButton.setOnClickListener{
            System.exit(0)
        }
        binding.optionsButton.setOnClickListener{
            val intent = Intent(this@MainMenuActivity, OptionsActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.leadBoardButton.setOnClickListener {

        }

    }
}