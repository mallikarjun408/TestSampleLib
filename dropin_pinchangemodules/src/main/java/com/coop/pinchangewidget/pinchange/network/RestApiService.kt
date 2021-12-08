package com.coop.pinchangewidget.pinchange.network


import com.coop.pinchangewidget.pinchange.model.PinChangeModel
import com.coop.pinchangewidget.pinchange.model.StatusResponse
import com.coop.pinchangewidget.pinchange.model.WrapKeyResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {

    @POST("connex/pin-change/wrap-key.get")
    fun getWrapperKeyAPI(@Body body: JsonObject): Call<WrapKeyResponse>

    @PUT("connex/pin-change")
    fun pinChangeAPI(@Body body: JsonObject): Call<PinChangeModel>

}