package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.nextButton.setOnClickListener{
            val username = binding.userNameID.text.toString()
            val password = binding.passwordID.text.toString()

            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener {
                    Toast.makeText(this, ("Account already exists."), Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    firebaseAuth.createUserWithEmailAndPassword(username,password)

                    val intent = Intent(this@RegisterActivity, MainMenuActivity::class.java)

                    Toast.makeText(this, ("Account successfully registered."), Toast.LENGTH_SHORT).show()

                    startActivity(intent)
                    finish()
                }
        }

        binding.userNameID.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val username = binding.userNameID.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches())
                    binding.userNameID.error = "Invalid username"
                else
                    binding.userNameID.error = null
            }
        }
    }
}