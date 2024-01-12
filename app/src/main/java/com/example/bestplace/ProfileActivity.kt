package com.example.bestplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import kotlin.collections.List

class ProfileActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        val logOutButton = findViewById<Button>(R.id.logOutButton)
        logOutButton.setOnClickListener {
            logOut()
        }

        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        homeButton.setOnClickListener {
            goToMenu()

        }
        val listButton = findViewById<ImageButton>(R.id.listButton)
        listButton.setOnClickListener {
            goToList()

        }
        val mapButton = findViewById<ImageButton>(R.id.mapButton2)
        mapButton.setOnClickListener {
            goToMap()

        }
        val profileButton = findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            goToProfile()

        }




    }

    fun goToMap () {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun goToMenu() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    fun goToList() {
        val intent = Intent(this, List::class.java)
        startActivity(intent)
    }
    fun goToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun logOut() {
        auth.signOut()
        // Navigera till inloggningsaktiviteten eller annan destination efter utloggning
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}