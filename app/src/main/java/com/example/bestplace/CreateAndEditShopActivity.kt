package com.example.bestplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.List

const val Shop_Position_key = "Shop_position"
const val POSITION_NOT_SET = -1

class CreateAndEditShopActivity : AppCompatActivity() {


    lateinit var nameEditText: EditText
    lateinit var cityEditText: EditText
    lateinit var categoryEditText : EditText
    lateinit var infoEditText: EditText

    lateinit var db : FirebaseFirestore // = FirebaseFirestore.getInstance()
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_and_edit_shop)

        db = Firebase.firestore
        auth = Firebase.auth

        nameEditText = findViewById(R.id.nameEditText)
        cityEditText = findViewById(R.id.cityEditText)
        categoryEditText = findViewById(R.id.categoryEditText)
        infoEditText = findViewById(R.id.infoEditText)


        val shopPosition = intent.getIntExtra(Shop_Position_key, POSITION_NOT_SET)
        val submitButton = findViewById<Button>(R.id.submitButton)

        if (shopPosition != POSITION_NOT_SET) { //Edit Shop
            showShop(shopPosition)
            submitButton.setOnClickListener {
                editShop(shopPosition)
            }
        } else {                        //Create Shop
            submitButton.setOnClickListener {
                addNewShop()
                val intent = Intent(this, List::class.java)
                startActivity(intent)
            }
        }

        //---------------------------------------------------------------

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


    fun showShop(position: Int) {
        val shop = DataManager.shopList[position]
        nameEditText.setText(shop.name)
        cityEditText.setText(shop.city)
        categoryEditText.setText(shop.category)
        infoEditText.setText(shop.info)

    }

    fun editShop (position: Int) {
       // DataManager.shopList[position].name = nameEditText.text.toString()
        //DataManager.shopList[position].city = cityEditText.text.toString()
        //DataManager.shopList[position].category = categoryEditText.text.toString()
        //DataManager.shopList[position].info = infoEditText.text.toString()
        //finish()

        val newName = nameEditText.text.toString()
        val newCategory = categoryEditText.toString()
        val newCity = cityEditText.toString()
        val newInfo = infoEditText.toString()

        val shop = DataManager.shopList[position]

        shop.name = newName
        shop.city = newCity
        shop.category = newCategory
        shop.info = newInfo

        val user = auth.currentUser
        if (user !=null){
            val shopId = shop.shopId

            if (shopId != null){
                val placeRef = db.collection("Shop").document(shopId)

                placeRef.update(
                    mapOf(
                        "name" to newName,
                        "category" to newCategory,
                        "city" to newCity,
                        "info" to newInfo
                    )
                ).addOnSuccessListener {
                    val intent = Intent(this, List::class.java)
                    startActivity(intent)
                }.addOnSuccessListener {
                    Toast.makeText(this, "Error when edit shop", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addNewShop() {
        val name = nameEditText.text.toString()
        val category = categoryEditText.text.toString()
        val city = cityEditText.text.toString()
        val info = infoEditText.text.toString()

        val shop = Shop(name = name, category = category, city = city, info = info)

        db.collection("Shop").add(shop)
            .addOnSuccessListener { documentReference ->
                Log.d("!!!", "DocumentSnapshot added to FB with ID: ${documentReference.id}")
                //finish()
            }
            .addOnFailureListener { e ->
                Log.d("!!!", "Error adding document", e)
            }
        DataManager.shopList.add(shop)


    }

}