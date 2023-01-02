package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityMainMenuBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

class MainMenuActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)




        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        val request = AdRequest.Builder().build()
        binding.adView2.loadAd(request)
        binding.menuPlayButton.setOnClickListener{
            // We preload the Ad
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
            //finish()
        }

        binding.menuLeadBoardButton.setOnClickListener {
            val intent = Intent(this@MainMenuActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }
}