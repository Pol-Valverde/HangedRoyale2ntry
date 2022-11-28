package com.example.hangedroyale2ntry

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
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
            checkLetter('q')
            Toast.makeText(this, "Q", Toast.LENGTH_SHORT).show()
        }
        binding.wButton.setOnClickListener{
            checkLetter('w')
            Toast.makeText(this, "W", Toast.LENGTH_SHORT).show()
        }
        binding.eButton.setOnClickListener{
            checkLetter('e')
            Toast.makeText(this, "E", Toast.LENGTH_SHORT).show()
        }
        binding.rButton.setOnClickListener{
            checkLetter('r')
            Toast.makeText(this, "R", Toast.LENGTH_SHORT).show()
        }
        binding.tButton.setOnClickListener{
            checkLetter('t')
            Toast.makeText(this, "T", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "A", Toast.LENGTH_SHORT).show()
        }
        binding.sButton.setOnClickListener{
            checkLetter('s')
            Toast.makeText(this, "S", Toast.LENGTH_SHORT).show()
        }
        binding.dButton.setOnClickListener{
            checkLetter('d')
            Toast.makeText(this, "D", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Z", Toast.LENGTH_SHORT).show()
        }
        binding.xButton.setOnClickListener{
            checkLetter('x')
            Toast.makeText(this, "X", Toast.LENGTH_SHORT).show()
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