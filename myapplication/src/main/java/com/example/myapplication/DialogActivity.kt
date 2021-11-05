package com.example.myapplication

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.CardlayoutBinding
import com.example.myapplication.databinding.EnterpinFragmentBinding

class DialogActivity: AppCompatActivity() {

    private lateinit var binding : EnterpinFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = EnterpinFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}