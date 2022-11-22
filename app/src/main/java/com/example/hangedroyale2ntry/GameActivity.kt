package com.example.hangedroyale2ntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    var currentWord: String = "calb"

    fun checkLetter(buttonLetter:Char){
        for (_letter in currentWord.iterator()) {
            println(_letter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "TESTEO FUERTE", Toast.LENGTH_SHORT).show()



        binding.qButton.setOnClickListener{
        Toast.makeText(this, "Macaco", Toast.LENGTH_SHORT).show()
        checkLetter('q')
        }

    }
}