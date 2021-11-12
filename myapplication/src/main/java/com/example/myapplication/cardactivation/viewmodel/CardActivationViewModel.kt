package com.example.myapplication.cardactivation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.cardactivation.network.RestApiService
import com.example.myapplication.utils.AppUtils
import org.json.JSONObject

class CardActivationViewModel: ViewModel(){

    private val TAG: String = CardActivationViewModel::class.java.getSimpleName()

    var clientUrl: String = ""
    val accNumberValidate = MutableLiveData<Boolean>()
    var accNumber: String =""


    fun validateAccountNumber(edt1: String,edt2: String, edt3: String,edt4: String) {

        accNumber = edt1+edt2+edt3+edt4

        accNumberValidate.value = AppUtils.isAccountNumberValid(accNumber)

    }

    fun activateCard(accNum: String){

        val header = HashMap<String, String>()

        header["client_id"] = ""
        header["Content_Type"] = "application/json"
        header["Accept"] = "*/*"
        header["client_secret"] = "269d98e4922fb3895e9ae2108cbb5064"
        header["client_id"] = "269d98e4922fb3895e9ae2108cbb5064"

        val body = HashMap<String, String>()

        

        // RestApiService.cardActivationAPI(clientUrl,)
    }

    fun isAccNumberValid(): LiveData<Boolean?>? {
        return accNumberValidate
    }


}