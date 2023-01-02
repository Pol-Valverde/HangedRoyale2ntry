package com.example.hangedroyale2ntry.gameScenes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.hangedroyale2ntry.databinding.ActivityMainMenuBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.example.hangedroyale2ntry.R
class MainMenuActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var mAdView : AdView

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Notifications:
        createNotificationChannel()

        // Ads:
        MobileAds.initialize(this)
        val request = AdRequest.Builder().build()
        binding.adView2.loadAd(request)

        // Listeners:
        binding.menuPlayButton.setOnClickListener{
            // Notification test:
            sendNotificiation()
            
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

    private fun createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotificiation()
    {
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.hud_face)
            .setContentTitle("Example Title")
            .setContentText("Example Description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
        }

    }
}