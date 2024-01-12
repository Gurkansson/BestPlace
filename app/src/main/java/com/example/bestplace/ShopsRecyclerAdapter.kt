package com.example.bestplace

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.collections.List

class ShopsRecyclerAdapter (
    val context : Context,
    val shopList: List<Shop>,
    val onItemClickListener : (Shop) -> Unit
) : RecyclerView.Adapter<ShopsRecyclerAdapter.ViewHolder>() {

    var db = Firebase.firestore
    val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = shopList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val shop = shopList[position]

        holder.nameTextView.text = shop.name
        holder.cityTextView.text = shop.city
        holder.shopPosition = position

        holder.itemView.setOnClickListener {
            Log.d("!!!", "ShopItem clicked at position $position")
            onItemClickListener(shop)
        }

    }

    fun removeShop(position: Int) {
        DataManager.shopList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val cityTextView = itemView.findViewById<TextView>(R.id.cityTextView)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
        var shopPosition = 0

        init {
            itemView.setOnClickListener {
                Log.d("!!!", "Item clicked at position $shopPosition")
               val intent = Intent(context, MoreInfoActivity::class.java)
                intent.putExtra(Shop_Position_key, shopPosition)
                context.startActivity(intent)
            }
            deleteButton.setOnClickListener {
                removeShop(shopPosition)
            }
        }





    }

}

