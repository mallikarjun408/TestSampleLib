package com.example.myapplication.cardactivation.data
import java.io.Serializable

class ClientData : Serializable {
    private lateinit var client_name: String
    private lateinit var header_card: String
    private lateinit var clientUrl: String

    fun getName(): String? {
        return client_name
    }
    @JvmName("setName")
    fun setName(name: String?) {
        this.client_name = name!!
    }
    fun getHeader(): String? {
        return header_card
    }
    @JvmName("setHeader")
    fun setHeader(cardHeader: String?) {
        this.header_card = cardHeader!!
    }

    fun getClientUrl(): String? {
        return clientUrl
    }

    @JvmName("setClientUrl")
    fun setClientUrl(client_url : String?) {
        this.clientUrl = client_url!!
    }



}