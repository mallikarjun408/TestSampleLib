package com.coop.pinchangewidget.pinchange.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coop.pinchangewidget.pinchange.model.PinChangeModel
import com.coop.pinchangewidget.pinchange.model.StatusResponse
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

    var dateLastMaintained : String? = null

    val accNumberValidate = MutableLiveData<Boolean>()
    val activateCardDetails = MutableLiveData<PinChangeModel>()
    val errorMessageData = MutableLiveData<MyErrorMessage>()
    val statusResponse = MutableLiveData<StatusResponse>()

    var accNumber: String =""



    fun validateAccountNumber(edt1: String,edt2: String, edt3: String,edt4: String) {

        accNumber = edt1+edt2+edt3+edt4

        accNumberValidate.value = AppUtils.isAccountNumberValid(accNumber) // CardValidator.validateLuhnNumber(accNumber)

    }

    fun getCardStatus () {

        val jsonObj = JSONObject()
        jsonObj.put("pan",accNumber) //4111111111111111, 5811280000912531


        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject

        val cardStatusAPI = apiService.getCardStatus( gsonObject)

        cardStatusAPI.enqueue(object:Callback<StatusResponse>{
            override fun onResponse(
                call: Call<StatusResponse>,
                response: Response<StatusResponse>
            ) {
               if(response.code() == 200){
                   statusResponse.value = response.body()
               } else {
                   errorMessageData.value =  Gson().fromJson(
                       response.errorBody()!!.charStream(),
                       MyErrorMessage::class.java
                   )
               }
            }

            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                activateCardDetails.value = PinChangeModel(t)
            }

        })
    }

    fun activateCardConnex (dateLastMaintained:String?) {

        val jsonObj = JSONObject()
        jsonObj.put("pan",accNumber)
        jsonObj.put("action","activate")
        jsonObj.put("dateLastMaintained",dateLastMaintained)

        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject
        val cardActivationAPI = apiService.cardActivationConnexAPI( gsonObject)
        cardActivationAPI.enqueue(object :Callback<PinChangeModel>{
            override fun onResponse(
                call: Call<PinChangeModel>,
                response: Response<PinChangeModel>
            ) {
                if(response.code() == 200)
                    activateCardDetails.value = response.body()
                else
                    errorMessageData.value =  Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        MyErrorMessage::class.java
                    )
            }

            override fun onFailure(call: Call<PinChangeModel>, t: Throwable) {
                Log.i(TAG,"Throwable")
                activateCardDetails.value = PinChangeModel(t)
            }
        })

    }
    fun activateCardOmaha () {

        val jsonObj = JSONObject()
        jsonObj.put("accountNumber",accNumber)  //  4111111111111111
        jsonObj.put("action","activate")

        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject
        val cardActivationAPI = apiService.cardActivationOmahaAPI( gsonObject)
        cardActivationAPI.enqueue(object :Callback<PinChangeModel>{
            override fun onResponse(
                call: Call<PinChangeModel>,
                response: Response<PinChangeModel>
            ) {
                if(response.code() == 200)
                    activateCardDetails.value = response.body()
                else
                    errorMessageData.value =  Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        MyErrorMessage::class.java
                    )
            }

            override fun onFailure(call: Call<PinChangeModel>, t: Throwable) {
                Log.i(TAG,"Throwable")
                activateCardDetails.value = PinChangeModel(t)
            }
        })

    }

    fun isAccNumberValid(): LiveData<Boolean?>? {
        return accNumberValidate
    }

    fun activateCardDetails(): LiveData<PinChangeModel>?{
        return activateCardDetails
    }
    fun errorMessage(): LiveData<MyErrorMessage>?{
        return errorMessageData
    }

    fun getStatusResponse(): LiveData<StatusResponse>?{
        return statusResponse
    }

}