package com.example.hangedroyale2ntry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityMainBinding
import com.google.firebase.auth.FederatedAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val username = binding.usernameID.text.toString()
            val password = binding.passwordID.text.toString()

            //Toast.makeText(this, "TEST",Toast.LENGTH_SHORT).show()

            firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnSuccessListener {
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this, "Pau Mira el correu",Toast.LENGTH_SHORT).show()
                }

        }
        binding.registerButton.setOnClickListener {
            val username = binding.usernameID.text.toString()
            val password = binding.passwordID.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(username,password)


        }

        binding.usernameID.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus) {
                val username  = binding.usernameID.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches())
                    binding.usernameID.error = "Invalid Username BOBO"
                else
                    binding.usernameID.error = null
            }

        }

    }
}