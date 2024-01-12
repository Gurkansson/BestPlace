package com.example.bestplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MoreInfoActivity : AppCompatActivity () {

    lateinit var showNameTextView: TextView
    lateinit var showCategoryTextView: TextView
    lateinit var showCityTextView: TextView
    lateinit var showInfoTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info)

        showNameTextView = findViewById(R.id.showNameTextView)
        showCategoryTextView = findViewById(R.id.showCategoryTextView)
        showCityTextView = findViewById(R.id.showCityTextView)
        showInfoTextView = findViewById(R.id.showInfoTextView)

        val getShopName = intent.getStringExtra("name")
        val getShopCategory = intent.getStringExtra("category")
        val getShopCity = intent.getStringExtra("city")
        val getShopInfo = intent.getStringExtra("info")

        showNameTextView.text = getShopName
        showCategoryTextView.text = getShopCategory
        showCityTextView.text = getShopCity
        showInfoTextView.text = getShopInfo

    }
}