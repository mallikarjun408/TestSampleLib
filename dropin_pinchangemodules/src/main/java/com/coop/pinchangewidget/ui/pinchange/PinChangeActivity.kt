package com.coop.pinchangewidget.ui.pinchange

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.view.Gravity
import com.coop.pinchangewidget.R
import com.coop.pinchangewidget.databinding.PinchangeActivityBinding
import com.coop.pinchangewidget.pinchange.viewmodel.PinChangeViewModel
import com.coop.pinchangewidget.pinchange.data.ClientData
import com.coop.pinchangewidget.pinchange.data.MySingleton


class PinChangeActivity : AppCompatActivity() {


    private lateinit var binding: PinchangeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        val window: Window = getWindow()
        window.setGravity(Gravity.BOTTOM)

        binding = PinchangeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = ""

        val intentData = intent.getSerializableExtra("client_data") as ClientData?

        MySingleton.baseUrl = intentData?.clientUrl.toString()
        MySingleton.client_id = intentData?.client_id.toString()
        MySingleton.client_secret = intentData?.client_secret.toString()
       // MySingleton.service_name=intentData?.service_name.toString()
        MySingleton.panNumber = intentData?.panNumber.toString()

        val createNewPinFragment = CreateNewPinFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentMain,createNewPinFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        val txtClose = binding.txtClose
        val txtHeader = binding.txtHeader

       // txtHeader.setText(intentData?.client_name)
        txtClose.setOnClickListener{
            finish()
        }
    }

}