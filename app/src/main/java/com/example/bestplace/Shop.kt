package com.example.bestplace

import com.google.firebase.firestore.DocumentId

data class Shop(@DocumentId
           var shopId: String? = null,
           var name: String? = null,
           var category: String? = null,
           var city: String? = null,
           var info: String?=null)  {

}
