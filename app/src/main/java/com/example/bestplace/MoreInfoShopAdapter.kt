package com.example.bestplace

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MoreInfoShopAdapter : AppCompatActivity() {

    lateinit var showName: TextView
    lateinit var showCategory: TextView
    lateinit var showCity: TextView
    lateinit var showInfo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info)

        showName = findViewById(R.id.showNameTextView)
        showCategory = findViewById(R.id.showCategoryTextView)
        showCity = findViewById(R.id.showCityTextView)
        showInfo = findViewById(R.id.showInfoTextView)

        val shopPosition = intent.getIntExtra(Shop_Position_key, POSITION_NOT_SET)
        showMoreInfoAboutShop(shopPosition)

        val getShopName = intent.getStringExtra("showName")
        val getShopCategory = intent.getStringExtra("showCategory")
        val getShopCity = intent.getStringExtra("showCity")
        val getShopInfo = intent.getStringExtra("showInfo")

        showName.text = getShopName
        showCategory.text = getShopCategory
        showCity.text = getShopCity
        showInfo.text = getShopInfo
    }

    fun showMoreInfoAboutShop(position:Int) {
        val shop = DataManager.shopList[position]
        showName.text = shop.name
        showCategory.text = shop.category
        showCity.text = shop.city
        showInfo.text = shop.info
    }



}