package com.example.myapplication.cardactivation.model

import com.google.gson.annotations.SerializedName

class CardData {

        @SerializedName("cardStatus")
         var cardStatus: CardStatus? = null

        @SerializedName("dateLastMaintained")
         var dateLastMaintained: String ? = null
}