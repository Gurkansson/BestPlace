package com.example.bestplace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestplace.DataManager.shopList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.List


class ListRecyclerViewActivity : AppCompatActivity() {
//
    lateinit var recyclerView: RecyclerView
    val db = Firebase.firestore
    lateinit var fab: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ShopsRecyclerAdapter(this, shopList) {shop ->
            val intent = Intent(this, MoreInfoActivity::class.java).apply {
                putExtra("name", shop.name)
                putExtra("category", shop.category)
                putExtra("city", shop.city)
                putExtra("info", shop.info)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter


        fab = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent (this, CreateAndEditShopActivity::class.java)
            startActivity(intent)
        }


        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("Shop")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents){
                    val restaturant = document.toObject(Shop::class.java)
                    restaturant?.let {
                        shopList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }



        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        homeButton.setOnClickListener {
             goToMenu()
        }
        val listButton = findViewById<ImageButton>(R.id.listButton)
        listButton.setOnClickListener {
            goToList()
        }
        val mapButton = findViewById<ImageButton>(R.id.mapButton)
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

        override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()

    }



}