package com.example.myapplication.ui.card

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.CardlayoutBinding
import com.example.myapplication.databinding.EnterpinFragmentBinding

class CardActivity : AppCompatActivity() {
    private lateinit var binding: CardlayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);

     //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = CardlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = ""


        val enterPinToActivateFragment = EnterPinToActivateFragment()

        // Get the support fragment manager instance
        val manager = supportFragmentManager
        // Begin the fragment transition using support fragment manager
        val transaction = manager.beginTransaction()

        // Replace the fragment on container
        transaction.replace(R.id.fragmentMain,enterPinToActivateFragment)
        transaction.addToBackStack(null)

        // Finishing the transition
        transaction.commit()

        val txtClose = binding.txtClose

        txtClose.setOnClickListener{
            finish()
        }
    }
}