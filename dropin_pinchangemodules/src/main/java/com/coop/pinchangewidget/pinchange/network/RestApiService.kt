package com.coop.pinchangewidget.pinchange.network


import com.coop.pinchangewidget.pinchange.model.PinChangeModel
import com.coop.pinchangewidget.pinchange.model.StatusResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {

    @POST("status/v2/connex/status.get")
    fun getCardStatus(@Body body: JsonObject): Call<StatusResponse>

    @PUT("card-activation/v2/connex/card-activation")
    fun cardActivationConnexAPI(@Body body: JsonObject): Call<PinChangeModel>

    @PUT("card-activation/v2/omaha/card-activation")
    fun cardActivationOmahaAPI(@Body body: JsonObject): Call<PinChangeModel>

}