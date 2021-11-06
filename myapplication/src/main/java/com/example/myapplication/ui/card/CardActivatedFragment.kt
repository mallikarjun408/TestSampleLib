package com.example.myapplication.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.EnterpinFragmentBinding

class CardActivatedFragment: Fragment() {

    private lateinit var binding: EnterpinFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get the custom view for this fragment layout

        // Return the fragment view/layout
        return inflater!!.inflate(R.layout.card_activation_completed, container, false)
    }
}