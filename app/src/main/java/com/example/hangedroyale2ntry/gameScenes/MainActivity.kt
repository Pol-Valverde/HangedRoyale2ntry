package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.util.Patterns
import com.example.hangedroyale2ntry.R
import com.example.hangedroyale2ntry.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var password:String =  "empty"
    var usernameID:String = "empty"
    var keepLoggedIn: Boolean = false

    companion object{
        var username: String = "empty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //playSound(R.raw.main_game_music, true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreference.edit()
        var userID  = sharedPreference.getString("username", username)
        var _password = sharedPreference.getString("password", password)

        keepLoggedIn = sharedPreference.getBoolean("KeepLogIn", keepLoggedIn)
        binding.switchLogIn.isChecked = keepLoggedIn

        login(userID.toString(),_password.toString(),editor)

        binding.switchLogIn.setOnClickListener{
            changeKeepLogIn()
            editor.putBoolean("KeepLogIn", keepLoggedIn)
            editor.apply()
        }

        binding.buttonguest.setOnClickListener{
            val intent = Intent(this@MainActivity, MainMenuActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.loginButton.setOnClickListener {
            username = binding.usernameID.text.toString()
            password = binding.passwordID.text.toString()
            login(username,password,editor)
        }

        binding.usernameID.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val username = binding.usernameID.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches())
                    binding.usernameID.error = "Invalid username"
                else
                    binding.usernameID.error = null
            }
        }
    }

    fun changeKeepLogIn() {
        keepLoggedIn = binding.switchLogIn.isChecked
    }

    fun login(user:String, pass:String, edit:SharedPreferences.Editor)
    {
        firebaseAuth.signInWithEmailAndPassword(user, pass)
            .addOnSuccessListener {
                if (keepLoggedIn) {
                    edit.putString("username", user)
                    edit.putString("password", pass)
                }
                edit.apply()
                val intent = Intent(this@MainActivity, MainMenuActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
        }
    }
}