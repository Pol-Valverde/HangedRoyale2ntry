package com.example.hangedroyale2ntry

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.hangedroyale2ntry.databinding.ActivityGameBinding
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    var hangManWord: String = ""
    var goalWord: String = "calb"
    var currentWord: String = ""
    var token:String = ""
    var boolLetter:Boolean = false
    /*fun checkLetter(buttonLetter: Char)
    {
        var i = 0
        for (letter in goalWord.iterator())
        {
            if(buttonLetter == letter)
            {
                currentWord.replaceRange(i, i, buttonLetter.toString())
            }
            i++
        }
        println(currentWord)
        binding.wordGame.text = currentWord
    }*/
    fun ShowOptions() {
        binding.optionsBackground.isVisible = true
        binding.optionsCanvas.isVisible = true
        binding.optionsBackButton.isVisible = true
        binding.optionsVolumeText.isVisible = true
        binding.optionsLenguageCanvas.isVisible = true
        binding.optionsLenguageText.isVisible = true
        binding.optionsChoosenText.isVisible = true
    }
    fun HideOptions() {
        binding.optionsBackground.isVisible = false
        binding.optionsCanvas.isVisible = false
        binding.optionsBackButton.isVisible = false
        binding.optionsVolumeText.isVisible = false
        binding.optionsLenguageCanvas.isVisible = false
        binding.optionsLenguageText.isVisible = false
        binding.optionsChoosenText.isVisible = false
    }
    fun HidePause() {
        binding.pauseBackground.isVisible = false
        binding.pauseCanvas.isVisible = false
        binding.pauseText.isVisible = false
        binding.pauseButtonHome.isVisible = false
        binding.pauseCanvasText.isVisible = false
        binding.pauseButtonOptions.isVisible = false
        binding.pauseButtonReturn.isVisible = false
    }
    fun ShowPause() {
        binding.pauseBackground.isVisible = true
        binding.pauseCanvas.isVisible = true
        binding.pauseText.isVisible = true
        binding.pauseButtonHome.isVisible = true
        binding.pauseCanvasText.isVisible = true
        binding.pauseButtonOptions.isVisible = true
        binding.pauseButtonReturn.isVisible = true
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextWord()



        binding.optionsBackButton.setOnClickListener {
            HideOptions()
        }
        binding.hudPauseButton.setOnClickListener {
            ShowPause()
        }
        binding.pauseButtonHome.setOnClickListener {
            val intent = Intent(this@GameActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.pauseButtonOptions.setOnClickListener {
            ShowOptions()
        }
        binding.pauseButtonReturn.setOnClickListener {
            HidePause()
        }
        Toast.makeText(this, "TESTEO FUERTE", Toast.LENGTH_SHORT).show()
        for (i in 0 .. goalWord.length - 1)
        {
            currentWord += "-"
        }
        binding.wordGame.text = currentWord
        println(goalWord)
        println(currentWord)
        binding.qButton.setOnClickListener{
            //Toast.makeText(this, "Q", Toast.LENGTH_SHORT).show()
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("Q",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()

        }
        binding.wButton.setOnClickListener{
            //Toast.makeText(this, "W", Toast.LENGTH_SHORT).show()
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("W",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.eButton.setOnClickListener{
            //Toast.makeText(this, "E", Toast.LENGTH_SHORT).show()
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("E",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.rButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("R",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.tButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("T",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.yButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("Y",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.uButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("U",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.iButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("I",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.oButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("O",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.pButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("P",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.aButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("A",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.sButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("S",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.dButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("D",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.fButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("F",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.gButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("G",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.hButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("H",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.jButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("J",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.kButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("K",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.lButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("L",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.zButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("Z",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.xButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("X",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.cButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("C",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.vButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("V",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.bButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("B",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.nButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("N",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.mButton.setOnClickListener{
            val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
            val services = outside.create(HangManInterface::class.java)
            services.checkLetter("M",token).enqueue(object : Callback<GuessLetterHangMan>
            {
                override fun onResponse(call: Call<GuessLetterHangMan>, response: Response<GuessLetterHangMan>
                ) {
                    val guessLetter = response.body()
                    token = guessLetter?.token ?: ""
                    boolLetter = guessLetter?.correct?:false
                }

                override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            Toast.makeText(this, boolLetter.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    fun nextWord()
    {
        val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
        val services = outside.create(HangManInterface::class.java)
        services.getHangmanWord().enqueue(object : Callback<ApiHangManGame> {
            override fun onResponse(call: Call<ApiHangManGame>, response: Response<ApiHangManGame>) {
                val hangManResult = response.body()
                hangManWord = hangManResult?.hangman ?: ""
                token = hangManResult?.token?:""
                print(hangManWord);

            }
            override fun onFailure(call: Call<ApiHangManGame>, t: Throwable) {
                print("Something went wrong")
            }
        })
    }
}