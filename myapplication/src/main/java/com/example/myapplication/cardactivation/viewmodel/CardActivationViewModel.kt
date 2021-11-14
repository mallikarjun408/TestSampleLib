package com.example.myapplication.cardactivation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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



class CardActivationViewModel: ViewModel(){

    private val TAG: String = CardActivationViewModel::class.java.getSimpleName()

    var clientUrl: String = ""
    val accNumberValidate = MutableLiveData<Boolean>()
    var accNumber: String =""


    fun validateAccountNumber(edt1: String,edt2: String, edt3: String,edt4: String) {

        accNumber = edt1+edt2+edt3+edt4

        accNumberValidate.value = AppUtils.isAccountNumberValid(accNumber)

    }

    fun activateCard(){

        val header = HashMap<String, String>()

        header["client_id"] = ""
        header["Content_Type"] = "application/json"
        header["Accept"] = "*/*"
        header["client_secret"] = "269d98e4922fb3895e9ae2108cbb5064"
        header["client_id"] = "269d98e4922fb3895e9ae2108cbb5064"


        val jsonObj = JSONObject()
        jsonObj.put("accountNumber",accNumber)
        jsonObj.put("action","activate")

        val body: RequestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
           jsonObj.toString()
        )

        //  val body: RequestBody = RequestBody.create(JSONStringer, jsonObj.toString())
        val cardActivationAPI = apiService.cardActivationAPI(clientUrl, header, jsonObj)
        cardActivationAPI.enqueue(object :Callback<CardActivationModel>{
            override fun onResponse(
                call: Call<CardActivationModel>,
                response: Response<CardActivationModel>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<CardActivationModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    fun isAccNumberValid(): LiveData<Boolean?>? {
        return accNumberValidate
    }


}