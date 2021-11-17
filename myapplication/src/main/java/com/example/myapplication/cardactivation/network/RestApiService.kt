package com.example.myapplication.cardactivation.network

import com.example.myapplication.cardactivation.model.CardActivationModel
import com.example.myapplication.cardactivation.model.StatusResponse
import com.google.gson.JsonObject
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {
   /* @get:GET("bQsLPRKKMi?indent=2")
    val userList: Call<UserWrapper?>? */

    @POST("status-api/2.0.6/m/connex/status.get")
    fun getCardStatus(@Body body: JsonObject): Call<StatusResponse>

    @PUT("card-activation-api/2.0.16/m/connex/card-activation")
    fun cardActivationConnexAPI(@Body body: JsonObject): Call<CardActivationModel>

    @PUT("card-activation-api/2.0.16/m/omaha/card-activation")
    fun cardActivationOmahaAPI(@Body body: JsonObject): Call<CardActivationModel>

}