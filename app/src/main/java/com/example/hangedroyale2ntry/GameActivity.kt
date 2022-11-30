package com.example.hangedroyale2ntry

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.hangedroyale2ntry.databinding.ActivityGameBinding
import com.example.hangedroyale2ntry.databinding.ActivityRegisterBinding
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    var hangManWord: String = ""
    var currentWord: String = ""
    var token: String = ""
    var boolLetter: Boolean = false
    var lives: Int = 5
    var faceAlpha: Int = 0
    var finishedWord: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hudHouseButton.setOnClickListener{
            val intent = Intent(this@GameActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.wordGame.text = hangManWord

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
        binding.dButton.setOnClickListener{ checkLetterButton("D"); binding.dButton.isEnabled = false }
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

    fun checkLetterButton(letter:String)
    {
        if (lives <= 0)
        {
            //Toast.makeText(this, ("Tremendo Malo"), Toast.LENGTH_SHORT).show()
        }
        if(finishedWord)
        {
            Toast.makeText(this, ("YouWin"), Toast.LENGTH_SHORT).show()
        }

        val outside = Retrofit.Builder().baseUrl("https://hangman-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
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
                    lives--;

                    faceAlpha += 75

                    binding.hudFace.setColorFilter(Color.argb(faceAlpha, 214, 25, 25))
                }
                if(!checkWordFinished(currentWord))
                {
                    finishedWord = true
                }
            }

            override fun onFailure(call: Call<GuessLetterHangMan>, t: Throwable) {
                TODO("Not yet implemented")
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
}