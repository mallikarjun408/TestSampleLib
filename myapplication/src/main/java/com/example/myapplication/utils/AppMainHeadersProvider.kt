package com.example.myapplication.utils

import android.net.wifi.rtt.CivicLocationKeys.LANGUAGE
import com.example.myapplication.BuildConfig
import com.example.myapplication.cardactivation.data.MySingleton

class ApiMainHeadersProvider {

    /**
     * Public headers for calls that do not need an authenticated user.
     */
    fun getAppHeaders(): AppHeaders =
        AppHeaders().apply {
            putAll(getDefaultHeaders())
        }

    /**
     * Default headers used on all calls.
     */
    private fun getDefaultHeaders(): Map<String, String> = mapOf(

        HEADER_ACCEPT to "*/*",
        CONTENT_TYPE to "application/json",
        CLIENT_ID to MySingleton.client_id,
        CLIENT_SECRET to MySingleton.client_secret
    )

    companion object {
        private const val HEADER_ACCEPT = "Accept"
        private const val CONTENT_TYPE = "Content-Type"
        private const val CLIENT_ID = "client_id"
        private const val CLIENT_SECRET = "client_secret"

    }
}

open class ApiMainHeaders : HashMap<String, String>()
class AppHeaders : ApiMainHeaders()