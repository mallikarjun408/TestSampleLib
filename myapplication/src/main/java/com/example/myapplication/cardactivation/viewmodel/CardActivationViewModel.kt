package com.example.myapplication.cardactivation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.cardactivation.data.MySingleton
import com.example.myapplication.cardactivation.model.CardActivationModel
import com.example.myapplication.cardactivation.model.StatusResponse
import com.example.myapplication.cardactivation.network.RetrofitInstance.apiService
import com.example.myapplication.utils.AppUtils
import retrofit2.Callback
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import com.google.gson.JsonObject

import com.google.gson.JsonParser
import com.example.myapplication.utils.MyErrorMessage
import com.google.gson.Gson


class CardActivationViewModel: ViewModel(){

    private val TAG: String = CardActivationViewModel::class.java.getSimpleName()

    var dateLastMaintained : String? = null

    val accNumberValidate = MutableLiveData<Boolean>()
    val activateCardDetails = MutableLiveData<CardActivationModel>()
    val errorMessageData = MutableLiveData<MyErrorMessage>()
    val statusResponse = MutableLiveData<StatusResponse>()

    var accNumber: String =""



    fun validateAccountNumber(edt1: String,edt2: String, edt3: String,edt4: String) {

        accNumber = edt1+edt2+edt3+edt4

        accNumberValidate.value = AppUtils.isAccountNumberValid(accNumber)

    }

    fun getCardStatus () {

        val jsonObj = JSONObject()
        jsonObj.put("pan","4111111111111111")


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
                activateCardDetails.value = CardActivationModel(t)
            }

        })
    }

    fun activateCardConnex (dateLastMaintained:String?) {

        val jsonObj = JSONObject()
        jsonObj.put("pan","4111111111111111")
        jsonObj.put("action","activate")
        jsonObj.put("dateLastMaintained",dateLastMaintained)

        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject
        val cardActivationAPI = apiService.cardActivationConnexAPI( gsonObject)
        cardActivationAPI.enqueue(object :Callback<CardActivationModel>{
            override fun onResponse(
                call: Call<CardActivationModel>,
                response: Response<CardActivationModel>
            ) {
                if(response.code() == 200)
                    activateCardDetails.value = response.body()
                else
                    errorMessageData.value =  Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        MyErrorMessage::class.java
                    )
            }

            override fun onFailure(call: Call<CardActivationModel>, t: Throwable) {
                Log.i(TAG,"Throwable")
                activateCardDetails.value = CardActivationModel(t)
            }
        })

    }
    fun activateCardOmaha () {

        val jsonObj = JSONObject()
        jsonObj.put("accountNumber","4111111111111111")
        jsonObj.put("action","activate")

        val jsonParser = JsonParser()
        var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject
        val cardActivationAPI = apiService.cardActivationOmahaAPI( gsonObject)
        cardActivationAPI.enqueue(object :Callback<CardActivationModel>{
            override fun onResponse(
                call: Call<CardActivationModel>,
                response: Response<CardActivationModel>
            ) {
                if(response.code() == 200)
                    activateCardDetails.value = response.body()
                else
                    errorMessageData.value =  Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        MyErrorMessage::class.java
                    )
            }

            override fun onFailure(call: Call<CardActivationModel>, t: Throwable) {
                Log.i(TAG,"Throwable")
                activateCardDetails.value = CardActivationModel(t)
            }
        })

    }

    fun isAccNumberValid(): LiveData<Boolean?>? {
        return accNumberValidate
    }

    fun activateCardDetails(): LiveData<CardActivationModel>?{
        return activateCardDetails
    }
    fun errorMessage(): LiveData<MyErrorMessage>?{
        return errorMessageData
    }

    fun getStatusResponse(): LiveData<StatusResponse>?{
        return statusResponse
    }

}