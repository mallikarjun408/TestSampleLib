package com.example.myapplication.cardactivation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.cardactivation.data.ClientData
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
    var clientId: String =""
    var clientSecret: String = ""

    val accNumberValidate = MutableLiveData<Boolean>()
    var accNumber: String =""



    fun validateAccountNumber(edt1: String,edt2: String, edt3: String,edt4: String) {

        accNumber = edt1+edt2+edt3+edt4

        accNumberValidate.value = AppUtils.isAccountNumberValid(accNumber)

    }

    fun activateCard(){

        val header = HashMap<String, String>()


        header["Content-Type"] = "application/json"
        header["Accept"] = "*/*"
        header["client_secret"] = clientSecret
        header["client_id"] = clientId




        val jsonObj = JSONObject()
        jsonObj.put("accountNumber","4111111111111111")
        jsonObj.put("action","activate")
        jsonObj.put("dateLastMaintained","2019-04-04:11:25:31.940000")

        val cardActivationAPI = apiService.cardActivationAPI( header, jsonObj)
        cardActivationAPI.enqueue(object :Callback<CardActivationModel>{
            override fun onResponse(
                call: Call<CardActivationModel>,
                response: Response<CardActivationModel>
            ) {

                Log.i("response cardActivation",response.message().toString())
            }

            override fun onFailure(call: Call<CardActivationModel>, t: Throwable) {
               Log.i(TAG,"Throwable")
            }

        })

    }

    fun isAccNumberValid(): LiveData<Boolean?>? {
        return accNumberValidate
    }


}