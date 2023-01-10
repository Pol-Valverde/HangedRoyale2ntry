package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.hangedroyale2ntry.leaderBoard.LeaderBoardItem
import com.example.hangedroyale2ntry.leaderBoard.LeaderBoardRecycleViewAdapter
import com.example.hangedroyale2ntry.databinding.ActivityLeaderBoardBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.example.hangedroyale2ntry.R

class LeaderBoardActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityLeaderBoardBinding
    private var adapter = LeaderBoardRecycleViewAdapter()
    private lateinit var database: DatabaseReference
    var musicMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        playMusic(R.raw.main_leaderboard_music, true)

        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database("https://hangedroyale2ntry-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users") // collection name
        binding.leaderBoardRecycler.adapter = adapter

        // loadLeaderBoard()

        binding.scoreHomeButton.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, MainMenuActivity::class.java)
            startActivity(intent)
            stopMusic()
            finish()
        }

        database.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                // Not working
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var userScores = ArrayList<Pair<String,Double>>()
                for(data in snapshot.children)
                {
                    var points = data.getValue<Double>()
                    val name: String = data.key?: "Null_Player"

                    userScores.add(Pair<String,Double>(name, points?: 0.0))
                }

                // Sort userScores:
                val orderedScores = userScores.sortedWith(compareBy{ it.second }).asReversed()

                val sortedList = arrayListOf<LeaderBoardItem>()

                orderedScores.forEachIndexed { index, pair ->
                    sortedList.add(LeaderBoardItem(index + 1, pair.first, pair.second))
                }

                adapter.updateList(sortedList)
            }
        })
    }

    private fun loadLeaderBoard()
    {
        // Read from Realtime Database:

        // Store values in pairs.

        // Fill userScores with items:

        // Sort userScores from highest points to lowest points:

        val userScores = mutableListOf<Pair<String, Double>>(
            Pair("Bob", 8008135.toDouble()), Pair("YuukiasTheSavior", 616616.toDouble()),
            Pair("TumyTheGodOfTechnology", 81729.toDouble()), Pair("MorataPiscinero", 8080.toDouble()),
            Pair("PolBestBasketballPlayer", 911.toDouble()), Pair("AkiohhhhhhhhMyGaad", 420.toDouble()),
            Pair("Skoindrix", 1.toDouble())
        )

        val sortedList = arrayListOf<LeaderBoardItem>()

        userScores.forEachIndexed { index, pair ->
            sortedList.add(LeaderBoardItem(index + 1, pair.first, pair.second))
        }

        adapter.updateList(sortedList)
    }

    fun playMusic(soundName: Int, loop: Boolean)
    {
        if (musicMediaPlayer == null)
        {
            musicMediaPlayer = MediaPlayer.create(this, soundName)
            musicMediaPlayer!!.isLooping = loop
            musicMediaPlayer!!.start()
        }
    }

    fun stopMusic() {
        if (musicMediaPlayer != null) {
            musicMediaPlayer!!.stop();
            musicMediaPlayer!!.release();
            musicMediaPlayer = null;
        }
    }
}