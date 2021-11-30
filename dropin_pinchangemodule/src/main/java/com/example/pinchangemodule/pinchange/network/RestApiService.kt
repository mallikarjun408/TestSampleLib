package com.example.pinchangemodule.pinchange.network

import com.coop.cardactivationwidget.cardactivation.model.CardActivationModel
import com.coop.cardactivationwidget.cardactivation.model.StatusResponse
import com.google.gson.JsonObject
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