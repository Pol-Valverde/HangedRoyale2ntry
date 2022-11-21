package com.example.hangedroyale2ntry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameID.text.toString()
            val password = binding.usernameID.text.toString()

//            CoroutineScope(Dispatchers.Default).launch {
//                delay(3000)
//            }

            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener {
                    val intent = Intent(this@MainActivity, GameActivity::class.java)
                    startActivity(intent)

                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "bobo", Toast.LENGTH_SHORT).show()
                }
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
}