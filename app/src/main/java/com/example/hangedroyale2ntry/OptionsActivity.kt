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

    var soundActivated: Boolean = true
    var notificationActivated:Boolean = true

    var userEmail: String? = ""
    var userConfig: UserConfig = UserConfig(userEmail.toString(), soundActivated, notificationActivated)
    var users = arrayListOf<UserConfig>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        usersCollection = firestore.collection(USERS_COLLECTION)

        firebaseAuth = FirebaseAuth.getInstance()
        userEmail = firebaseAuth.currentUser?.email

        // Firestore logic: <-- EL GET NO VA POL ---------------------------------------------------------------------------------------------------
        usersCollection.get().addOnSuccessListener {
            users = it?.documents?.mapNotNull { dbUser ->
                dbUser.toObject(UserConfig::class.java)
            } as ArrayList<UserConfig>

            val currentUser = users.find{
                isUserCurrentUser(it)
            }

            // Sound options logic:
            if (currentUser?.soundOn == true)
            {
                soundActivated = true

                binding.soundSwitch.isChecked = true
            }
            else
            {
                soundActivated = false

                binding.soundSwitch.isChecked = false
            }

            // Notification options logic:
            if (currentUser?.notificationsOn == true)
            {
                notificationActivated = true

                binding.notificationSwitch.isChecked = true
            }
            else
            {
                notificationActivated = false

                binding.notificationSwitch.isChecked = false
            }
        }

        // Button logic:
        binding.soundSwitch.setOnClickListener{
            soundActivated = !soundActivated

            updateFireStore()
        }

        binding.notificationSwitch.setOnClickListener {
            notificationActivated = !notificationActivated

            updateFireStore()
        }
    }

    fun updateFireStore()
    {
        // Set Firestore values:
        userConfig = UserConfig(userEmail.toString(), soundActivated, notificationActivated)

        usersCollection.document(userEmail.toString()).set(userConfig)
    }

    fun isUserCurrentUser(user: UserConfig): Boolean
    {
        return user.email == userEmail
    }
}