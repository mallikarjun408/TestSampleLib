package com.coop.pinchangewidget.pinchange.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coop.pinchangewidget.pinchange.data.MySingleton
import com.coop.pinchangewidget.pinchange.model.PinChangeModel
import com.coop.pinchangewidget.pinchange.model.StatusResponse
import com.coop.pinchangewidget.pinchange.model.WrapKeyResponse
import com.coop.pinchangewidget.pinchange.network.RetrofitInstance.apiService
import com.coop.pinchangewidget.utils.AppUtils
import com.coop.pinchangewidget.utils.MyErrorMessage
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PinChangeViewModel: ViewModel(){

    private val TAG: String = PinChangeViewModel::class.java.getSimpleName()

    val isPinValidate = MutableLiveData<Boolean>()
    val errorMessageData = MutableLiveData<MyErrorMessage>()

   // val pinChangeThrowable = MutableLiveData<PinChangeModel>()


    // wraper key
    val wraperKeyResponse = MutableLiveData<WrapKeyResponse>()

    // pinChangeResponse
    val pinChangeResponse = MutableLiveData<PinChangeModel>()


    fun validatePin(pin: String,re_pin: String) {

        isPinValidate.value = pin == re_pin
    }

    fun getWraperKey () {

        val jsonObj = JSONObject()
        var wraperRandomKey = AppUtils.generateWraperRandomKey()
        jsonObj.put("pinKey",wraperRandomKey)

        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject

        val wraperKeyAPI = apiService.getWrapperKeyAPI( gsonObject)

        wraperKeyAPI.enqueue(object:Callback<WrapKeyResponse>{
            override fun onResponse(
                call: Call<WrapKeyResponse>,
                response: Response<WrapKeyResponse>
            ) {
               if(response.code() == 200){
                   wraperKeyResponse.value = response.body()
               } else {
                   errorMessageData.value =  Gson().fromJson(
                       response.errorBody()!!.charStream(),
                       MyErrorMessage::class.java
                   )
               }
            }

            override fun onFailure(call: Call<WrapKeyResponse>, t: Throwable) {
                pinChangeResponse.value = PinChangeModel(t)
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun pinChangeAPICall (panNumber:String, wrappedKey:String, newPin:String) {

        var encryptedPin = AppUtils.generateKeyEncryptedBase64String(newPin)

        val jsonObj = JSONObject()
        /*jsonObj.put("pan","5811280000929360")
        jsonObj.put("wrappedKey","cHatV7GmFGnu_KGh_26jila3PyzNICA9gKTdQw6l5w1aZcQR2qomwoLZ4Bdo0q5EPhqbfMCwCeNkg7mYWqg6l5_zemHHw-R8L0j1kM4Qgyr0Leklp6THvxEmWyfUw0AxunMoSzhS6T7VDJ9O6s-FAKo3YH5xPpAN8BMKQW5KDFrvQpHm8Z_MUc80E26dIArHIXA5Idzw83g2M4MQtC984LcUu0XcfRMEXFNc17Gu_2V-806vvEikBvz_zKoz-fCr6OrudKP4egzHLqzEnJou_jJJDLdZlz0VIdzuOxZAUy-r4DEK7nRJL13EirEMxmKfdYDuybz50IrCfywmNshxtA")
        jsonObj.put("newPin","eyJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiQTI1NkdDTUtXIiwiaXYiOiJkMmpCa3hKMkxVXzRfOWQ0IiwidGFnIjoiTHd0bGtZYUhwQWh0OUN0ZVhlZG5pQSJ9.rxQO_LFP7SWRi8rPV6VX6ALgSmiI8CRj-1AY01bUfnE.GbVDvUBSUg6l9Gfi.dkAqPw.DWd1Wq-VgA_jy8EifZHToA")
*/

        jsonObj.put("pan","5811280000929360")
        jsonObj.put("wrappedKey",wrappedKey)
        jsonObj.put("newPin",encryptedPin)


        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject

        val pinChangeAPI = apiService.pinChangeAPI( gsonObject)

        pinChangeAPI.enqueue(object:Callback<PinChangeModel>{
            override fun onResponse(
                call: Call<PinChangeModel>,
                response: Response<PinChangeModel>
            ) {
                if(response.code() == 200){
                    pinChangeResponse.value = response.body()
                } else {
                    errorMessageData.value =  Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        MyErrorMessage::class.java
                    )
                }
            }

            override fun onFailure(call: Call<PinChangeModel>, t: Throwable) {
                pinChangeResponse.value = PinChangeModel(t)
            }

        })
    }
}
