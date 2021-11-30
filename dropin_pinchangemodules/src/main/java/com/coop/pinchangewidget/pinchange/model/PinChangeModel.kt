package com.coop.pinchangewidget.pinchange.model

import com.google.gson.annotations.SerializedName

class PinChangeModel {

    @SerializedName("success")
     var isSuccess: Boolean? = null

    @SerializedName("message")
     var message: String? = null

    @SerializedName("returnCode")
     var returnCode: Integer? = null

    @SerializedName("data")
     var data: String? = null

    private var error: Throwable ? = null

    constructor(err: Throwable){
        error = err
    }

    fun getError():Throwable?{
        return  error
    }

    fun setError(err:Throwable){
        error = err
    }
}