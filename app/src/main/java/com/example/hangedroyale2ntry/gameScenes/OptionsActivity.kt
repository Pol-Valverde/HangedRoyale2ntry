package com.example.hangedroyale2ntry.gameScenes

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.hangedroyale2ntry.dataClasses.UserConfig
import com.example.hangedroyale2ntry.databinding.ActivityOptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class OptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var usersCollection: CollectionReference
    private lateinit var sharedPreference :SharedPreferences
    private val USERS_COLLECTION: String = "users"

    var soundActivated: Boolean = true
    var notificationActivated:Boolean = true
    var userEmail: String? = ""
    var puntuation:Int = 0
    var userConfig: UserConfig = UserConfig(userEmail.toString(), soundActivated, notificationActivated,puntuation)
    var users = arrayListOf<UserConfig>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        usersCollection = firestore.collection(USERS_COLLECTION)
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)

        firebaseAuth = FirebaseAuth.getInstance()
        userEmail = firebaseAuth.currentUser?.email
        userConfig = UserConfig(userEmail.toString(),soundActivated,notificationActivated,puntuation)
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

                binding.optionsSoundSwitch.isChecked = true
            }
            else
            {
                soundActivated = false

                binding.optionsSoundSwitch.isChecked = false
            }

            // Notification options logic:
            if (currentUser?.notificationsOn == true)
            {
                notificationActivated = true

                binding.optionsNotificationSwitch.isChecked = true
            }
            else
            {
                notificationActivated = false

                binding.optionsNotificationSwitch.isChecked = false
            }
        }

        // Button logic:
        binding.optionsSoundSwitch.setOnClickListener{
            soundActivated = !soundActivated

            updateFireStore()
        }
        binding.optionsLogOutButton.setOnClickListener{
            val editor = sharedPreference.edit()
            editor.remove("username")
            editor.remove("password")
            editor.remove("keepLogIn")
            editor.clear().apply()

            val intent = Intent(this@OptionsActivity, MainActivity::class.java)
            startActivity(intent)

            finish()
        }

        binding.optionsNotificationSwitch.setOnClickListener {
            notificationActivated = !notificationActivated

            updateFireStore()
        }

        binding.optionsHomeButton.setOnClickListener {
            val intent = Intent(this@OptionsActivity, MainMenuActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

    fun updateFireStore()
    {
        // Set Firestore values:
        userConfig = UserConfig(userEmail.toString(), soundActivated, notificationActivated,puntuation)

        usersCollection.document(userEmail.toString()).set(userConfig)
    }

    fun isUserCurrentUser(user: UserConfig): Boolean
    {
        return user.email == userEmail
    }
}