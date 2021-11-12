package com.example.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.cardactivation.data.ClientData
import com.example.myapplication.cardactivation.viewmodel.CardActivationViewModel
import com.example.myapplication.ui.card.CardActivity
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
            val clientData = ClientData()
            clientData.setName("Card Activation")
            clientData.setHeader("xxxx")
            clientData.setClientUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/902a620f-1bc1-4977-8c8d-933022d52108/card-activation-api/2.0.16/m/omaha/card-activation")

            val bundle = Bundle()
            bundle.putSerializable("client_data", clientData)
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("brandName","Chase")
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}