package com.example.myapplication.ui.card

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.EnterpinFragmentBinding

class EnterPinToActivateFragment: Fragment(){

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
        val view: View = inflater.inflate(R.layout.enterpin_fragment, container, false)

        val btnActivate: Button = view.findViewById(R.id.btnActivate)
        // Get the support fragment manager instance
        val manager = activity?.supportFragmentManager
        btnActivate.setOnClickListener {

            val cardActivatedFragment = CardActivatedFragment()
            // Begin the fragment transition using support fragment manager
            val transaction = manager?.beginTransaction()

            // Replace the fragment on container
            transaction?.replace(R.id.fragmentMain,cardActivatedFragment)
            transaction?.addToBackStack(null)

            // Finishing the transition
            transaction?.commit()
        }
        // Return the fragment view/layout
        return view;
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}