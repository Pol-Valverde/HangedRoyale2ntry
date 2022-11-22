package com.example.hangedroyale2ntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    var goalWord: String = "calb"
    var currentWord: String = ""

    fun checkLetter(buttonLetter: Char)
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
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "TESTEO FUERTE", Toast.LENGTH_SHORT).show()

        for (i in 0 .. goalWord.length - 1)
        {
            currentWord += "-"
        }
        binding.wordGame.text = currentWord
        println(goalWord)
        println(currentWord)

        binding.qButton.setOnClickListener{
        checkLetter('q')
        }
        binding.wButton.setOnClickListener{
            checkLetter('w')
        }
        binding.eButton.setOnClickListener{
            checkLetter('e')
        }
        binding.rButton.setOnClickListener{
            checkLetter('r')
        }
        binding.tButton.setOnClickListener{
            checkLetter('t')
        }
        binding.yButton.setOnClickListener{
            checkLetter('y')
        }
        binding.uButton.setOnClickListener{
            checkLetter('u')
        }
        binding.iButton.setOnClickListener{
            checkLetter('i')
        }
        binding.oButton.setOnClickListener{
            checkLetter('o')
        }
        binding.pButton.setOnClickListener{
            checkLetter('p')
        }
        binding.aButton.setOnClickListener{
            checkLetter('a')
        }
        binding.sButton.setOnClickListener{
            checkLetter('s')
        }
        binding.dButton.setOnClickListener{
            checkLetter('d')
        }
        binding.fButton.setOnClickListener{
            checkLetter('f')
        }
        binding.gButton.setOnClickListener{
            checkLetter('g')
        }
        binding.hButton.setOnClickListener{
            checkLetter('h')
        }
        binding.jButton.setOnClickListener{
            checkLetter('j')
        }
        binding.kButton.setOnClickListener{
            checkLetter('k')
        }
        binding.lButton.setOnClickListener{
            checkLetter('l')
        }
        binding.zButton.setOnClickListener{
            checkLetter('z')
        }
        binding.xButton.setOnClickListener{
            checkLetter('x')
        }
        binding.cButton.setOnClickListener{
            checkLetter('c')
        }
        binding.vButton.setOnClickListener{
            checkLetter('v')
        }
        binding.bButton.setOnClickListener{
            checkLetter('b')
        }
        binding.nButton.setOnClickListener{
            checkLetter('n')
        }
        binding.mButton.setOnClickListener{
            checkLetter('m')
        }


    }
}