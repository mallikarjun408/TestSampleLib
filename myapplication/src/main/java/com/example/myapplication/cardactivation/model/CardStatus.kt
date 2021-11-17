package com.example.myapplication.cardactivation.model

import com.google.gson.annotations.SerializedName

class CardStatus {

    @SerializedName("status")
     var status: String ? = null

    @SerializedName("subStatus")
     var subStatus: String ? = null

    @SerializedName("host")
     var host: String ? = null
}