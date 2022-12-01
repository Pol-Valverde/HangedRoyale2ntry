package com.example.hangedroyale2ntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangedroyale2ntry.databinding.ActivityLeaderBoardBinding

class LeaderBoardActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityLeaderBoardBinding
    private var adapter = LeaderBoardRecycleViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // supportActionBar?.hide() // Aquesta funció serveix per ocultar la barra de dalt on surt al títol de cada Activity

        binding.leaderBoardRecycler.adapter = adapter

        loadLeaderBoard()
    }

    private fun loadLeaderBoard()
    {
        val userScores = listOf<Pair<String, Int>>(
            Pair("Bob", 8008135), Pair("YuukiasTheSavior", 616616),
            Pair("TumyTheGodOfTechnology", 81729), Pair("MorataPiscinero", 8080),
            Pair("PolBestBasketballPlayer", 911), Pair("AkiohhhhhhhhMyGaad", 420),
            Pair("Skoindrix", 1)
        )

        val sortedList = arrayListOf<LeaderBoardItem>()

        userScores.forEachIndexed { index, pair ->
            sortedList.add(LeaderBoardItem(index + 1, pair.first, pair.second))
        }

        adapter.updateList(sortedList)
    }
}