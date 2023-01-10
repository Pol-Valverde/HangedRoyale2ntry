package com.example.hangedroyale2ntry.gameScenes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import com.example.hangedroyale2ntry.databinding.ActivityMainMenuBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.example.hangedroyale2ntry.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.system.exitProcess


class MainMenuActivity : AppCompatActivity()
{
    private val firebaseAnalytics = Firebase.analytics
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var mAdView : AdView

    companion object{
        var musicMediaPlayer: MediaPlayer? = null
    }

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        playMusic(R.raw.main_menu_music, true)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Notifications:
        createNotificationChannel()
        fireBaseNotification()

        // Ads:
        MobileAds.initialize(this)
        val request = AdRequest.Builder().build()
        binding.adView2.loadAd(request)

        // Analytics

        // Listeners:
        binding.menuPlayButton.setOnClickListener{
            // Analytics:
            firebaseAnalytics.logEvent("StartPlaying",null)
            val intent = Intent(this@MainMenuActivity, GameActivity::class.java)
            stopMusic()
            startActivity(intent)
            finish()
        }

        binding.menuExitButton.setOnClickListener{
            stopMusic()
            finish()
        }

        binding.menuOptionsButton.setOnClickListener{
            val intent = Intent(this@MainMenuActivity, OptionsActivity::class.java)
            startActivity(intent)
        }

        binding.menuLeadBoardButton.setOnClickListener {
            val intent = Intent(this@MainMenuActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            stopMusic()
            finish()
        }
    }
    private fun fireBaseNotification()
    {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful) {
                Toast.makeText(this, ("Fetching FCM registration token failed"), Toast.LENGTH_SHORT).show()
                return@OnCompleteListener
            }

            val token = task.result
            println(token)
        })
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