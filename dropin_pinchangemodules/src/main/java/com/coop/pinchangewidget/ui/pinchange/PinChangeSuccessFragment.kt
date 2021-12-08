package com.coop.pinchangewidget.ui.pinchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.coop.pinchangewidget.R

class PinChangeSuccessFragment  : Fragment() {

    var selectedPos = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get the custom view for this fragment layout
        val view: View = inflater.inflate(R.layout.pinchange_success_fragment, container, false)
        // Return the fragment view/layout
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnClose = view.findViewById<Button>(R.id.btnClose)


        btnClose.setOnClickListener {
            activity?.finish()
        }
    }


}