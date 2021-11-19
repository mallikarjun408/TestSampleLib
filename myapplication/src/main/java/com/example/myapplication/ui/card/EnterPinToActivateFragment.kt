package com.example.myapplication.ui.card

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.cardactivation.data.MySingleton
import com.example.myapplication.cardactivation.viewmodel.CardActivationViewModel
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress


class EnterPinToActivateFragment: Fragment(){

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
        // Return the fragment view/layout
        return view;
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mViewModel: CardActivationViewModel = ViewModelProvider(requireActivity()).get(
            CardActivationViewModel::class.java
        )
        val btnActivate: Button = view.findViewById(R.id.btnActivate)
        val edt1 = view.findViewById<EditText>(R.id.edt_one)
        val edt2 = view.findViewById<EditText>(R.id.edt_two)
        val edt3 = view.findViewById<EditText>(R.id.edt_three)
        val edt4 = view.findViewById<EditText>(R.id.edt_four)
        val txtAccNumMsg = view.findViewById<TextView>(R.id.txtAccNumMsg)

        bindProgressButton(btnActivate)
        btnActivate.attachTextChangeAnimator()
        btnActivate.setOnClickListener {
            btnActivate.showProgress {
                buttonTextRes = R.string.empty
                progressColor = Color.WHITE
            }

            if(MySingleton.service_name == "connex")
                mViewModel.getCardStatus()
            else
                mViewModel.activateCardOmaha()
        }

        edt1.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length ==4)
                    edt2.requestFocus()
                mViewModel.validateAccountNumber(edt1.text.toString(),edt2.text.toString(),edt3.text.toString(),edt4.text.toString())

            }
        })
        edt2.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length ==4)
                    edt3.requestFocus()
                mViewModel.validateAccountNumber(edt1.text.toString(),edt2.text.toString(),edt3.text.toString(),edt4.text.toString())

            }
        })
        edt3.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length ==4)
                    edt4.requestFocus()
                mViewModel.validateAccountNumber(edt1.text.toString(),edt2.text.toString(),edt3.text.toString(),edt4.text.toString())

            }
        })

        edt4.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length ==4) {
                    val inputMethodManager =
                        activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

                    mViewModel.validateAccountNumber(edt1.text.toString(),edt2.text.toString(),edt3.text.toString(),edt4.text.toString())
                }
            }
        })
        mViewModel.accNumberValidate.observe(viewLifecycleOwner, Observer<Boolean> { isAccNumberValid ->
            // Update the UI
            txtAccNumMsg.visibility = View.VISIBLE
            if(isAccNumberValid){
                btnActivate.isEnabled = true
                txtAccNumMsg.text = getString(R.string.accnum_valid_success)
                txtAccNumMsg.setTextColor(getColor(txtAccNumMsg.context,R.color.card_number_valid_color))

            }else{
                btnActivate.isEnabled = false
                txtAccNumMsg.text = getString(R.string.accnum_invalid_error)
                txtAccNumMsg.setTextColor(getColor(txtAccNumMsg.context,R.color.card_number_invalid_color))
            }
        })

        mViewModel.activateCardDetails.observe(
            viewLifecycleOwner,
            {activateCardDetails ->

                if(activateCardDetails.isSuccess == true)
                    navigateToCardActivateSuccessScreen()
                else
                    txtAccNumMsg.text = getString(R.string.accnum_invalid_error)
                    txtAccNumMsg.setTextColor(getColor(txtAccNumMsg.context,R.color.card_number_invalid_color))

                btnActivate.hideProgress(R.string.card_activate)
            }
        )
        mViewModel.errorMessageData.observe(viewLifecycleOwner,{errorMessageData ->

            btnActivate.hideProgress(R.string.card_activate)

           // txtAccNumMsg.text =   errorMessageData.code


            if(errorMessageData.description !== null){
                txtAccNumMsg.text = errorMessageData.description
            }else if(errorMessageData.code == null){
                txtAccNumMsg.text =   errorMessageData.error
            }else{
                txtAccNumMsg.text = errorMessageData.code
            }

            txtAccNumMsg.setTextColor(getColor(txtAccNumMsg.context,R.color.card_number_invalid_color))
        })

        mViewModel.statusResponse.observe(viewLifecycleOwner,{statusResponse ->
            val dateLastMaintained = statusResponse?.data?.dateLastMaintained
            mViewModel.dateLastMaintained = dateLastMaintained
            Log.i("","")
            mViewModel.activateCardConnex(dateLastMaintained)
        })
    }

    private fun navigateToCardActivateSuccessScreen() {
        // Get the support fragment manager instance
        val manager = activity?.supportFragmentManager

        val cardActivatedFragment = CardActivatedFragment()
        val transaction = manager?.beginTransaction()
        transaction?.replace(R.id.fragmentMain,cardActivatedFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
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