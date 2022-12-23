package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangedroyale2ntry.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuPlayButton.setOnClickListener{
            val intent = Intent(this@MainMenuActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.menuExitButton.setOnClickListener{
            finish()
        }

        binding.menuOptionsButton.setOnClickListener{
            val intent = Intent(this@MainMenuActivity, OptionsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.menuLeadBoardButton.setOnClickListener {
            val intent = Intent(this@MainMenuActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}