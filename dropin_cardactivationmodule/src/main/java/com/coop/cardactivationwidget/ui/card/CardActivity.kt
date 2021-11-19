package com.coop.cardactivationwidget.ui.card

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coop.cardactivationwidget.R
import com.coop.cardactivationwidget.cardactivation.data.ClientData
import com.coop.cardactivationwidget.cardactivation.data.MySingleton
import com.coop.cardactivationwidget.cardactivation.viewmodel.CardActivationViewModel
import com.coop.cardactivationwidget.databinding.CardlayoutBinding
import android.view.Gravity




class CardActivity : AppCompatActivity() {
    private lateinit var binding: CardlayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        val window: Window = getWindow()
        window.setGravity(Gravity.BOTTOM )

        binding = CardlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mViewModel: CardActivationViewModel = ViewModelProvider(this).get(
            CardActivationViewModel::class.java
        )

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = ""

        val intentData = intent.getSerializableExtra("client_data") as ClientData?

        MySingleton.baseUrl = intentData?.clientUrl.toString()
        MySingleton.client_id = intentData?.client_id.toString()
        MySingleton.client_secret = intentData?.client_secret.toString()
        MySingleton.service_name=intentData?.service_name.toString()

        val enterPinToActivateFragment = EnterPinToActivateFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentMain,enterPinToActivateFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        val txtClose = binding.txtClose
        val txtHeader = binding.txtHeader

        txtHeader.setText(intentData?.client_name)
        txtClose.setOnClickListener{
            finish()
        }


    }
}