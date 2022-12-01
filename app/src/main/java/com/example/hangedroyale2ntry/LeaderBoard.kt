package com.example.hangedroyale2ntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangedroyale2ntry.databinding.ActivityLeaderBoardBinding


class LeaderBoard : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}