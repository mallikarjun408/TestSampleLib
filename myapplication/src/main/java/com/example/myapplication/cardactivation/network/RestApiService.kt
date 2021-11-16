package com.example.myapplication.cardactivation.network

import com.example.myapplication.cardactivation.model.CardActivationModel
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {
   /* @get:GET("bQsLPRKKMi?indent=2")
    val userList: Call<UserWrapper?>? */

    @PUT("card-activation")
    fun cardActivationAPI(@HeaderMap header: Map<String,String>, @Body body: JSONObject): Call<CardActivationModel>



}