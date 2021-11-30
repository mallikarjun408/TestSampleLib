package com.example.pinchangemodule.pinchange.model

import com.google.gson.annotations.SerializedName

class StatusResponse {

    @SerializedName("data")
     var data: CardData ? = null

    @SerializedName("message")
     var message: String? = null

    @SerializedName("returnCode")
     var returnCode: String? = null

    @SerializedName("success")
     var success: String? = null

}



