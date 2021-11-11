package com.example.myapplication.cardactivation.model

import com.google.gson.annotations.SerializedName




class CardActivationModel {

    /*
    * {
         "success": true,
          "message": "Operation completed successfully.",
          "returnCode": 201,
          "data": true
       }
    * */

    @SerializedName("success")
    private var isSuccess: String? = null

    @SerializedName("message")
    private var message: String? = null

    @SerializedName("returnCode")
    private var returnCode: Integer? = null

    @SerializedName("data")
    private var data: String? = null

    fun getIsSuccess(): String? {
        return isSuccess
    }

    fun setIsSuccess(success: String) {
        isSuccess = success
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(msg: String) {
        message = msg
    }

    fun getReturnCode(): Integer? {
        return returnCode
    }

    fun setReturnCode(code: Integer) {
        returnCode = code
    }

    fun getData(): String? {
        return data
    }

    fun setData(mData: String) {
        data = mData
    }
}