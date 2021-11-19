package com.example.myapplication.cardactivation.network

import com.example.myapplication.cardactivation.model.CardActivationModel
import com.example.myapplication.cardactivation.model.StatusResponse
import com.google.gson.JsonObject
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {

    @POST("status/v2/connex/status.get")
    fun getCardStatus(@Body body: JsonObject): Call<StatusResponse>

    @PUT("card-activation/v2/connex/card-activation")
    fun cardActivationConnexAPI(@Body body: JsonObject): Call<CardActivationModel>

    @PUT("card-activation/v2/omaha/card-activation")
    fun cardActivationOmahaAPI(@Body body: JsonObject): Call<CardActivationModel>

}