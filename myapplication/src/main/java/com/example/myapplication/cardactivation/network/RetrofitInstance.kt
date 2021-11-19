package com.example.myapplication.cardactivation.network

import com.example.myapplication.cardactivation.data.ClientData
import com.example.myapplication.cardactivation.data.MySingleton
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.Request
import java.lang.Appendable


object RetrofitInstance {

    private var retrofit: Retrofit? = null
    val apiService: RestApiService

        get() {

            val oktHttpClient = OkHttpClient.Builder()
                .connectTimeout(
                   1,
                    TimeUnit.MINUTES
                )
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)

            oktHttpClient.addInterceptor { chain ->
                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept","*/*")
                    .addHeader("client_secret",MySingleton.client_secret)
                    .addHeader("client_id",MySingleton.client_id)
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(MySingleton.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build()
            }
            return retrofit!!.create(RestApiService::class.java)
        }
}