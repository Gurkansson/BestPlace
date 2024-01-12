package com.example.bestplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        homeButton.setOnClickListener {
            // goToMenu()
            //Ingen funktion då vi redan är på den sidan i appen
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
        val intent = Intent(this, ListRecyclerViewActivity::class.java)
        startActivity(intent)
    }

    fun goToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}