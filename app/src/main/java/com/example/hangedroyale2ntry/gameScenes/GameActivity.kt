package com.example.hangedroyale2ntry.gameScenes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.hangedroyale2ntry.dataClasses.ApiHangManGame
import com.example.hangedroyale2ntry.dataClasses.GuessLetterHangMan
import com.example.hangedroyale2ntry.otherFiles.HangManInterface
import com.example.hangedroyale2ntry.otherFiles.TimerService
import com.example.hangedroyale2ntry.databinding.ActivityGameBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt
import com.google.android.gms.ads.rewarded.RewardedAd
import android.view.View
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions
import com.example.hangedroyale2ntry.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val firebaseAnalytics = Firebase.analytics
    private lateinit var database: DatabaseReference
    var hangManWord: String = "______"
    var currentWord: String = ""
    var token: String = ""
    var boolLetter: Boolean = false
    var lives: Int = 7
    var faceAlpha: Int = 0
    var finishedWord: Boolean = false
    val urlApi = "http://hangman.enti.cat:5002/new/"
    var boolAd: Boolean = false
    //counter
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private var mRewardedAd: RewardedAd? = null
    private final var TAG = "MainMenuActivity"
    var mMediaPlayer: MediaPlayer? = null
    var musicMediaPlayer: MediaPlayer? = null
    var paused: Boolean = false
    var points: Int = 0

    var username: String = "Guest-20905372"


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        playMusic(R.raw.main_game_music, true)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreference.edit()
        var userID  = sharedPreference.getString("username", MainActivity.username)
        username = userID.toString()

        database = Firebase.database("https://hangedroyale2ntry-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users") // collection name

        MobileAds.initialize(this){}
        var adRequest = AdRequest.Builder().build()

        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback(){
            override fun onAdLoaded(ad: RewardedAd) {
                //Log.d(TAG, "Ad was loaded.")
                mRewardedAd = ad
                val options = ServerSideVerificationOptions.Builder()
                    .setCustomData("SAMPLE_CUSTOM_DATA_STRING")
                    .build()
                mRewardedAd?.setServerSideVerificationOptions(options)
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                //Log.d(TAG, adError?.toString())
                mRewardedAd = null
            }


        })

        mRewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                //Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // If this works:
                mRewardedAd
                // Set the ad reference to null so you don't show the ad a second time.
                //Log.d(TAG, "Ad dismissed fullscreen content.")
                mRewardedAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                //Log.e(TAG, "Ad failed to show fullscreen content.")
                mRewardedAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                //Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                //Log.d(TAG, "Ad showed fullscreen content.")
            }

        }

        //chrono logic
        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        startStopTimer()

        //buttons logic
        binding.pauseButton.setOnClickListener {
            if(!paused) {
                playSound(R.raw.go_forward, false)
                binding.pauseButton.isVisible = false
                binding.unpauseButton.isVisible = true
                binding.pauseGrey.isVisible = true
                binding.pausedText.isVisible = true
                binding.qButton.isClickable = false
                binding.wButton.isClickable = false
                binding.eButton.isClickable = false
                binding.rButton.isClickable = false
                binding.tButton.isClickable = false
                binding.yButton.isClickable = false
                binding.uButton.isClickable = false
                binding.iButton.isClickable = false
                binding.oButton.isClickable = false
                binding.pButton.isClickable = false
                binding.aButton.isClickable = false
                binding.sButton.isClickable = false
                binding.dButton.isClickable = false
                binding.fButton.isClickable = false
                binding.gButton.isClickable = false
                binding.hButton.isClickable = false
                binding.jButton.isClickable = false
                binding.kButton.isClickable = false
                binding.lButton.isClickable = false
                binding.zButton.isClickable = false
                binding.xButton.isClickable = false
                binding.cButton.isClickable = false
                binding.vButton.isClickable = false
                binding.bButton.isClickable = false
                binding.nButton.isClickable = false
                binding.mButton.isClickable = false
                paused = true
            }
            startStopTimer()
        }

        binding.unpauseButton.setOnClickListener {
            if (paused) {
                playSound(R.raw.go_back, false)
                binding.pauseButton.isVisible = true
                binding.unpauseButton.isVisible = false
                binding.pauseGrey.isVisible = false
                binding.pausedText.isVisible = false
                binding.qButton.isClickable = true
                binding.wButton.isClickable = true
                binding.eButton.isClickable = true
                binding.rButton.isClickable = true
                binding.tButton.isClickable = true
                binding.yButton.isClickable = true
                binding.uButton.isClickable = true
                binding.iButton.isClickable = true
                binding.oButton.isClickable = true
                binding.pButton.isClickable = true
                binding.aButton.isClickable = true
                binding.sButton.isClickable = true
                binding.dButton.isClickable = true
                binding.fButton.isClickable = true
                binding.gButton.isClickable = true
                binding.hButton.isClickable = true
                binding.jButton.isClickable = true
                binding.kButton.isClickable = true
                binding.lButton.isClickable = true
                binding.zButton.isClickable = true
                binding.xButton.isClickable = true
                binding.cButton.isClickable = true
                binding.vButton.isClickable = true
                binding.bButton.isClickable = true
                binding.nButton.isClickable = true
                binding.mButton.isClickable = true
                paused = false
            }
            startStopTimer()
        }

        binding.hudRetryButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_forward, false)
            resetTimer()
            val intent = Intent(this@GameActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.hudHouseButton.setOnClickListener{
            stopMusic()
            playSound(R.raw.go_back, false)
            resetTimer()
            val intent = Intent(this@GameActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.youWinReloadButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_forward, false)
            resetTimer()
            val intent = Intent(this@GameActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.youWinHomeButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_back, false)
            resetTimer()
            val intent = Intent(this@GameActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.youWinLeaderboardButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_forward, false)
            resetTimer()
            val intent = Intent(this@GameActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.youLoseReloadButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_forward, false)
            firebaseAnalytics.logEvent("new_chance"){
                param("boolAd","false")
            }
            resetTimer()
            val intent = Intent(this@GameActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.youLoseHomeButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_back, false)
            resetTimer()
            firebaseAnalytics.logEvent("new_chance"){
                param("boolAd","false")
            }
            val intent = Intent(this@GameActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.youLoseLeaderboardButton.setOnClickListener {
            stopMusic()
            playSound(R.raw.go_forward, false)
            resetTimer()
            firebaseAnalytics.logEvent("new_chance"){
                param("boolAd","false")
            }
            val intent = Intent(this@GameActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            finish()
        }

        nextWord()

        binding.qButton.setOnClickListener{
            checkLetterButton("Q")
            binding.qButton.isEnabled = false
        }

        binding.wButton.setOnClickListener{
            checkLetterButton("W")
            binding.wButton.isEnabled = false
        }
        binding.eButton.setOnClickListener{
            checkLetterButton("E")
            binding.eButton.isEnabled = false
        }
        binding.rButton.setOnClickListener{
            checkLetterButton("R")
            binding.rButton.isEnabled = false
        }
        binding.tButton.setOnClickListener{
            checkLetterButton("T")
            binding.tButton.isEnabled = false
        }
        binding.yButton.setOnClickListener{
            checkLetterButton("Y")
            binding.yButton.isEnabled = false
        }
        binding.uButton.setOnClickListener{
            checkLetterButton("U")
            binding.uButton.isEnabled = false
        }
        binding.iButton.setOnClickListener{
            checkLetterButton("I")
            binding.iButton.isEnabled = false
        }
        binding.oButton.setOnClickListener{
            checkLetterButton("O")
            binding.oButton.isEnabled = false
        }
        binding.pButton.setOnClickListener{
            checkLetterButton("P")
            binding.pButton.isEnabled = false
        }
        binding.aButton.setOnClickListener{
            checkLetterButton("A")
            binding.aButton.isEnabled = false
        }
        binding.sButton.setOnClickListener{
            checkLetterButton("S")
            binding.sButton.isEnabled = false
        }
        binding.dButton.setOnClickListener{
            checkLetterButton("D")
            binding.dButton.isEnabled = false
        }
        binding.fButton.setOnClickListener{
            checkLetterButton("F")
            binding.fButton.isEnabled = false
        }
        binding.gButton.setOnClickListener{
            checkLetterButton("G")
            binding.gButton.isEnabled = false
        }
        binding.hButton.setOnClickListener{
            checkLetterButton("H")
            binding.hButton.isEnabled = false
        }
        binding.jButton.setOnClickListener{
            checkLetterButton("J")
            binding.jButton.isEnabled = false
        }
        binding.kButton.setOnClickListener{
            checkLetterButton("K")
            binding.kButton.isEnabled = false
        }
        binding.lButton.setOnClickListener{
            checkLetterButton("L")
            binding.lButton.isEnabled = false
        }
        binding.zButton.setOnClickListener{
            checkLetterButton("Z")
            binding.zButton.isEnabled = false
        }
        binding.xButton.setOnClickListener{
            checkLetterButton("X")
            binding.xButton.isEnabled = false
        }
        binding.cButton.setOnClickListener{
            checkLetterButton("C")
            binding.cButton.isEnabled = false
        }
        binding.vButton.setOnClickListener{
            checkLetterButton("V")
            binding.vButton.isEnabled = false
        }
        binding.bButton.setOnClickListener{
            checkLetterButton("B")
            binding.bButton.isEnabled = false
        }
        binding.nButton.setOnClickListener{
            checkLetterButton("N")
            binding.nButton.isEnabled = false
        }
        binding.mButton.setOnClickListener{
            checkLetterButton("M")
            binding.mButton.isEnabled = false
        }

        binding.AdButton.setOnClickListener { // Logic to show ad:
            playSound(R.raw.go_forward, false)
            stopMusic()

            if(mRewardedAd!=null)
            {
                mRewardedAd?.show(this, OnUserEarnedRewardListener() {
                    fun onUserEarnedReward(rewardItem: RewardItem) {

                        var rewardAmount = rewardItem.amount
                        var rewardType = rewardItem.type
                    }
                    lives = 5
                    faceAlpha = 0
                    binding.hudFace.setColorFilter(Color.argb(faceAlpha, 214, 25, 25))
                    binding.youLoseBackground.isVisible = false
                    binding.youLoseSphere1.isVisible = false
                    binding.youLoseSphere2.isVisible = false
                    binding.youLoseSphere3.isVisible = false
                    binding.youLoseScoreCanvas.isVisible = false
                    binding.youLoseFace.isVisible = false
                    binding.youLoseHomeButton.isVisible = false
                    binding.youLoseText.isVisible = false
                    binding.youLoseLeaderboardButton.isVisible = false
                    binding.youLoseReloadButton.isVisible = false
                    binding.youLoseScoreText.isVisible = false
                    binding.AdButton.isVisible = false
                    binding.qButton.isClickable = true
                    binding.wButton.isClickable = true
                    binding.eButton.isClickable = true
                    binding.rButton.isClickable = true
                    binding.tButton.isClickable = true
                    binding.yButton.isClickable = true
                    binding.uButton.isClickable = true
                    binding.iButton.isClickable = true
                    binding.oButton.isClickable = true
                    binding.pButton.isClickable = true
                    binding.aButton.isClickable = true
                    binding.sButton.isClickable = true
                    binding.dButton.isClickable = true
                    binding.fButton.isClickable = true
                    binding.gButton.isClickable = true
                    binding.hButton.isClickable = true
                    binding.jButton.isClickable = true
                    binding.kButton.isClickable = true
                    binding.lButton.isClickable = true
                    binding.zButton.isClickable = true
                    binding.xButton.isClickable = true
                    binding.cButton.isClickable = true
                    binding.vButton.isClickable = true
                    binding.bButton.isClickable = true
                    binding.nButton.isClickable = true
                    binding.mButton.isClickable = true
                    boolAd = true
                    playMusic(R.raw.main_game_music, true)
                    firebaseAnalytics.logEvent("new_chance"){
                        param("boolAd","true")
                    }
                })
            } else {
                Toast.makeText(this, ("Can't load Ad"), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun nextWord()
    {
        val outside = Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create()).build()
        val services = outside.create(HangManInterface::class.java)
        services.getHangmanWord().enqueue(object : Callback<ApiHangManGame> {
            override fun onResponse(call: Call<ApiHangManGame>, response: Response<ApiHangManGame>) {
                val hangManResult = response.body()
                hangManWord = hangManResult?.hangman ?: ""
                token = hangManResult?.token?:""
                binding.wordGame.text = hangManWord
                print(hangManWord);
            }
            override fun onFailure(call: Call<ApiHangManGame>, t: Throwable) {
                print("Something went wrong")
            }
        })
    }

    fun showWin() {

        playSound(R.raw.you_win, false)
        binding.youWinBackground.isVisible = true
        binding.youWinSphere1.isVisible = true
        binding.youWinSphere2.isVisible = true
        binding.youWinSphere3.isVisible = true
        binding.youWinScoreCanvas.isVisible = true
        binding.youWinFace.isVisible = true
        binding.youWinHomeButton.isVisible = true
        binding.youWinText.isVisible = true
        binding.youWinLeaderboardButton.isVisible = true
        binding.youWinReloadButton.isVisible = true
        binding.youWinScoreText.text = points.toString()
        binding.youWinScoreText.isVisible = true
        binding.qButton.isClickable = false
        binding.wButton.isClickable = false
        binding.eButton.isClickable = false
        binding.rButton.isClickable = false
        binding.tButton.isClickable = false
        binding.yButton.isClickable = false
        binding.uButton.isClickable = false
        binding.iButton.isClickable = false
        binding.oButton.isClickable = false
        binding.pButton.isClickable = false
        binding.aButton.isClickable = false
        binding.sButton.isClickable = false
        binding.dButton.isClickable = false
        binding.fButton.isClickable = false
        binding.gButton.isClickable = false
        binding.hButton.isClickable = false
        binding.jButton.isClickable = false
        binding.kButton.isClickable = false
        binding.lButton.isClickable = false
        binding.zButton.isClickable = false
        binding.xButton.isClickable = false
        binding.cButton.isClickable = false
        binding.vButton.isClickable = false
        binding.bButton.isClickable = false
        binding.nButton.isClickable = false
        binding.mButton.isClickable = false

        database.child(username.replace('.','_').replace('#','_').replace('$','_').replace('[','_').replace(']','_').replace('/','_')).setValue(points.toDouble())
    }

    fun showLose() {
        playSound(R.raw.you_lose, false)
        binding.youLoseBackground.isVisible = true
        binding.youLoseSphere1.isVisible = true
        binding.youLoseSphere2.isVisible = true
        binding.youLoseSphere3.isVisible = true
        binding.youLoseScoreCanvas.isVisible = true
        binding.youLoseFace.isVisible = true
        binding.youLoseHomeButton.isVisible = true
        binding.youLoseText.isVisible = true
        binding.youLoseLeaderboardButton.isVisible = true
        binding.youLoseReloadButton.isVisible = true
        binding.youLoseScoreText.text = points.toString()
        binding.youLoseScoreText.isVisible = true
        binding.qButton.isClickable = false
        binding.wButton.isClickable = false
        binding.eButton.isClickable = false
        binding.rButton.isClickable = false
        binding.tButton.isClickable = false
        binding.yButton.isClickable = false
        binding.uButton.isClickable = false
        binding.iButton.isClickable = false
        binding.oButton.isClickable = false
        binding.pButton.isClickable = false
        binding.aButton.isClickable = false
        binding.sButton.isClickable = false
        binding.dButton.isClickable = false
        binding.fButton.isClickable = false
        binding.gButton.isClickable = false
        binding.hButton.isClickable = false
        binding.jButton.isClickable = false
        binding.kButton.isClickable = false
        binding.lButton.isClickable = false
        binding.zButton.isClickable = false
        binding.xButton.isClickable = false
        binding.cButton.isClickable = false
        binding.vButton.isClickable = false
        binding.bButton.isClickable = false
        binding.nButton.isClickable = false
        binding.mButton.isClickable = false
        if(boolAd == false)
            binding.AdButton.isVisible = true
    }

    fun checkLetterButton(letter:String)
    {
        if (lives <= 0)
        {
            showLose()
        }

        val outside = Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create()).build()
        val services = outside.create(HangManInterface::class.java)
        services.checkLetter(letter,token).enqueue(object : Callback<GuessLetterHangMan>
        {
            override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
            ) {
                //response.code() == 304

                val guessLetter = response.body()
                token = guessLetter?.token ?: ""
                boolLetter = guessLetter?.correct?:false
                currentWord = guessLetter?.hangman?:"empty"
                binding.wordGame.text = currentWord
                if(!boolLetter)
                {
                    playSound(R.raw.incorrect, false)
                    lives--;
                    faceAlpha += 75
                    points-=1
                    binding.hudFace.setColorFilter(Color.argb(faceAlpha, 214, 25, 25))
                }
                else
                {
                    playSound(R.raw.correct, false)
                    points+=5
                }


                if(!checkWordFinished(currentWord))
                {
                    showWin()
                    points+=50
                    //CALL REALTIME
                }
            }

            override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                print("error")
            }
        })
    }

    fun checkWordFinished(word:String): Boolean
    {
        val result = word.any{
            it == '_'
        }

        return result
    }

    private val updateTime:BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
            binding.ChronoID.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time:Double):String
    {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60
        return makeTimeString(hours,minutes,seconds)
    }

    private fun makeTimeString(hour: Int, min:Int,sec:Int): String = String.format("%02d:%02d:%02d", hour, min, sec)

    private fun startStopTimer()
    {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
        startService(serviceIntent)
        timerStarted = true
    }

    private fun stopTimer() {

        stopService(serviceIntent)
        timerStarted = false
    }

    private fun resetTimer()
    {
        stopTimer()
        time = 0.0
        binding.ChronoID.text = getTimeStringFromDouble(time)
    }

    //MUSIC ADDED
    fun playSound(soundName: Int, loop: Boolean)
    {
        mMediaPlayer = null

        if (mMediaPlayer == null)
        {
            mMediaPlayer = MediaPlayer.create(this, soundName)
            mMediaPlayer!!.isLooping = loop
            mMediaPlayer!!.start()
        }
        else mMediaPlayer!!.start()
    }

    fun playMusic(soundName: Int, loop: Boolean)
    {
        musicMediaPlayer = null

        if (musicMediaPlayer == null)
        {
            musicMediaPlayer = MediaPlayer.create(this, soundName)
            musicMediaPlayer!!.isLooping = loop
            musicMediaPlayer!!.start()
        }
        else musicMediaPlayer!!.start()
    }

    fun stopMusic() {
        if (musicMediaPlayer != null) {
            musicMediaPlayer!!.stop()
            musicMediaPlayer!!.release()
            musicMediaPlayer = null
        }
    }
}