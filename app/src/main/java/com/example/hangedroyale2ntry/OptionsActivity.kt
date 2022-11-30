package com.example.hangedroyale2ntry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hangedroyale2ntry.databinding.ActivityOptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class OptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var usersCollection: CollectionReference

    private val USERS_COLLECTION: String = "users"

    var soundActivated: Boolean = false
    var notificationActivated:Boolean = false

    var userID: String? = ""
    val userConfig: UserConfig = UserConfig("", soundActivated, notificationActivated)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        usersCollection = firestore.collection(USERS_COLLECTION)

        firebaseAuth = FirebaseAuth.getInstance()
        userID = firebaseAuth.currentUser?.email

        // Firestore logic:
            // Button logic:
                // UpdateFireStore()

        binding.soundButton.setOnClickListener{
            soundActivated = !soundActivated

            UpdateFireStore()
        }
    }

    fun UpdateFireStore()
    {
        // Set Firestore values:
        usersCollection.document(userID.toString()).set(userConfig)

        Toast.makeText(this, ("ME CAGO EN DIOS"), Toast.LENGTH_SHORT).show()
    }
}