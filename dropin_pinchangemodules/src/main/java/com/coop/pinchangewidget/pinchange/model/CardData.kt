package com.coop.pinchangewidget.pinchange.model

import com.google.gson.annotations.SerializedName

class CardData {

        @SerializedName("cardStatus")
         var cardStatus: CardStatus? = null

        @SerializedName("dateLastMaintained")
         var dateLastMaintained: String ? = null
}