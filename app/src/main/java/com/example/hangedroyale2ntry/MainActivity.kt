package com.example.hangedroyale2ntry

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.util.Patterns
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var password:String =  "empty"
    var usernameID:String = "empty"


    companion object{
        var username: String = "empty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreference.edit()
        var userID  = sharedPreference.getString("username", null)
        var _password = sharedPreference.getString("password", null)

        Toast.makeText(this, userID, Toast.LENGTH_SHORT).show()
        Login(userID.toString(),_password.toString(),editor)

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
            Login(username,password,editor)
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

    fun Login(user:String,pass:String,edit:SharedPreferences.Editor)
    {
        firebaseAuth.signInWithEmailAndPassword(user, pass)
            .addOnSuccessListener {

                edit.putString("username",user)
                edit.putString("password", pass)
                Toast.makeText(this, pass, Toast.LENGTH_SHORT).show()
                edit.apply()
                val intent = Intent(this@MainActivity, MainMenuActivity::class.java)
                startActivity(intent)

                finish()
            }.addOnFailureListener {

            }
    }
}