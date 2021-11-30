package com.example.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coop.cardactivationwidget.cardactivation.data.ClientData
import com.coop.cardactivationwidget.ui.card.CardActivity
import com.coop.pinchangewidget.ui.pinchange.PinChangeActivity
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val btnCardActivate = binding.buttonFirst
        val btnPinChange = binding.buttonSecond

        btnCardActivate.setOnClickListener {

        /*    Android Module Integration Mandatory Code Starts */
            val clientData = ClientData()
            clientData.client_name = "Card Activation"
            clientData.clientUrl = getText(R.string.base_url).toString()
            clientData.client_id = getText(R.string.client_id).toString()
            clientData.client_secret = getText(R.string.client_secret).toString()
            clientData.service_name = getText(R.string.service_name).toString()

            val bundle = Bundle()
            bundle.putSerializable("client_data", clientData)
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        /*    Android Module Integration Mandatory Code Ends */
        }

        btnPinChange.setOnClickListener {

            /*    Android Module Integration Mandatory Code Starts */
            val clientData = com.coop.pinchangewidget.pinchange.data.ClientData()
            clientData.client_name = "Card Activation"
            clientData.clientUrl = getText(R.string.base_url).toString()
            clientData.client_id = getText(R.string.client_id).toString()
            clientData.client_secret = getText(R.string.client_secret).toString()
            clientData.service_name = getText(R.string.service_name).toString()

            val bundle = Bundle()
            bundle.putSerializable("client_data", clientData)
            val intent = Intent(this, PinChangeActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

            /*    Android Module Integration Mandatory Code Ends */
        }
    }
}