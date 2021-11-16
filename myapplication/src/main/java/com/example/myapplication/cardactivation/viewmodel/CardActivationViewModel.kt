package com.example.myapplication.cardactivation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.cardactivation.data.ClientData
import com.example.myapplication.cardactivation.data.MySingleton
import com.example.myapplication.cardactivation.model.CardActivationModel
import com.example.myapplication.cardactivation.network.RestApiService
import com.example.myapplication.cardactivation.network.RetrofitInstance.apiService
import com.example.myapplication.utils.AppUtils
import retrofit2.Callback
import okhttp3.MediaType
import org.json.JSONObject
import okhttp3.RequestBody
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Response
import com.google.gson.JsonObject

import com.google.gson.JsonParser
import com.example.myapplication.utils.MyErrorMessage
import com.google.gson.Gson












class CardActivationViewModel: ViewModel(){

    private val TAG: String = CardActivationViewModel::class.java.getSimpleName()

    var clientUrl: String = ""
    var clientId: String =""
    var clientSecret: String = ""

    val accNumberValidate = MutableLiveData<Boolean>()
    val activateCardDetails = MutableLiveData<CardActivationModel>()
    val errorMessageData = MutableLiveData<MyErrorMessage>()

    var accNumber: String =""



    fun validateAccountNumber(edt1: String,edt2: String, edt3: String,edt4: String) {

        accNumber = edt1+edt2+edt3+edt4

        accNumberValidate.value = AppUtils.isAccountNumberValid(accNumber)

    }

    fun activateCard (){

        val header = HashMap<String, String>()
        header["Content-Type"] = "application/json"
        header["Accept"] = "*/*"
        header["client_secret"] = MySingleton.client_secret
        header["client_id"] = MySingleton.client_id

        val jsonObj = JSONObject()
        jsonObj.put("pan","4111111111111111")
        jsonObj.put("action","activat")
        jsonObj.put("dateLastMaintained","2019-04-04:11:25:31.940000")

        val jsonParser = JsonParser()
       var gsonObject = jsonParser.parse(jsonObj.toString()) as JsonObject
        val cardActivationAPI = apiService.cardActivationAPI( header, gsonObject)
        cardActivationAPI.enqueue(object :Callback<CardActivationModel>{
            override fun onResponse(
                call: Call<CardActivationModel>,
                response: Response<CardActivationModel>
            ) {
                val i = Log.i("response cardActivation", response.message().toString())

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

}