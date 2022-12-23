package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangedroyale2ntry.leaderBoard.LeaderBoardItem
import com.example.hangedroyale2ntry.leaderBoard.LeaderBoardRecycleViewAdapter
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

        binding.leaderBoardRecycler.adapter = adapter

        loadLeaderBoard()

        binding.scoreHomeButton.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, MainMenuActivity::class.java)
            startActivity(intent)

            finish()
        }
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