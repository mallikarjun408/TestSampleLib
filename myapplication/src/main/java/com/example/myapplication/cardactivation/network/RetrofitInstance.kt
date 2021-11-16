package com.example.myapplication.cardactivation.network

import com.example.myapplication.cardactivation.data.ClientData
import com.example.myapplication.cardactivation.data.MySingleton
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


object RetrofitInstance {

    // https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/902a620f-1bc1-4977-8c8d-933022d52108/card-activation-api/2.0.16/m/connex/
    private var retrofit: Retrofit? = null
    var clientData = ClientData()
    val apiService: RestApiService

        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/902a620f-1bc1-4977-8c8d-933022d52108/card-activation-api/2.0.16/m/connex/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(RestApiService::class.java)
        }
}