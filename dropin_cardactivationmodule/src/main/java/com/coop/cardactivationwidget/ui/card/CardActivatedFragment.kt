package com.coop.cardactivationwidget.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.coop.cardactivationwidget.R
import com.coop.cardactivationwidget.databinding.CardActivationCompletedBinding

class CardActivatedFragment: Fragment() {

    private lateinit var binding: CardActivationCompletedBinding

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
        val mView =  inflater!!.inflate(R.layout.card_activation_completed, container, false)

        val btnDone = mView.findViewById<Button>(R.id.btnCompleted)

        btnDone.setOnClickListener {
            activity?.finish()
        }

        return mView;
    }


}