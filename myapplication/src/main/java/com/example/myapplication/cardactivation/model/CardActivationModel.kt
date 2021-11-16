package com.example.myapplication.cardactivation.model

import com.google.gson.annotations.SerializedName

class CardActivationModel {


    /* @SerializedName("coord")
     var coord: String? = null
     @SerializedName("sys")
     var sys: String? = null*/

    /*
    * {
         "success": true,
          "message": "Operation completed successfully.",
          "returnCode": 201,
          "data": true
       }
    * */

    @SerializedName("success")
    private var isSuccess: Boolean? = null

    @SerializedName("message")
    private var message: String? = null

    @SerializedName("returnCode")
    private var returnCode: Integer? = null

    @SerializedName("data")
    private var data: String? = null

    @SerializedName("code")
    private var code: String? = null

    private var error: Throwable ? = null

    constructor(err: Throwable){
        error = err
    }

    fun getIsSuccess(): Boolean? {
        return isSuccess
    }

    fun setIsSuccess(success: Boolean) {
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

    fun getCode(): String? {
        return code
    }

    fun setCode(mCode: String) {
        code = mCode
    }

    fun getError():Throwable?{
        return  error
    }

    fun setError(err:Throwable){
        error = err
    }
}