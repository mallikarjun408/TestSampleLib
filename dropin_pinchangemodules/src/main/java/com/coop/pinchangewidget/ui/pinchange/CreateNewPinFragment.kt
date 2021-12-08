package com.coop.pinchangewidget.ui.pinchange

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coop.pinchangewidget.R
import com.coop.pinchangewidget.pinchange.data.MySingleton
import com.coop.pinchangewidget.pinchange.viewmodel.PinChangeViewModel
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress

class CreateNewPinFragment  : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.create_newpin_fragment, container, false)

        return view;
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mViewModel: PinChangeViewModel = ViewModelProvider(requireActivity()).get(
            PinChangeViewModel::class.java
        )
        val btnSelect = view.findViewById<Button>(R.id.btnSelect)
        val edtEnterPin = view.findViewById<EditText>(R.id.edt_enterpin)
        val edtReEnterPin = view.findViewById<EditText>(R.id.edt_reenterpin)
        val txtErrorView = view.findViewById<TextView>(R.id.txt_errorview)
        bindProgressButton(btnSelect)
        btnSelect.attachTextChangeAnimator()

        btnSelect.setOnClickListener {
            btnSelect.showProgress {
                buttonTextRes = R.string.empty
                progressColor = Color.WHITE
            }
            mViewModel.getWraperKey()
        }

        edtEnterPin.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length == 4)
                    edtReEnterPin.requestFocus()

            }
        })

        edtReEnterPin.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length == 4)
                    mViewModel.validatePin(edtEnterPin.text.toString(),edtReEnterPin.text.toString())
            }
        })

        mViewModel.isPinValidate.observe(viewLifecycleOwner,{isPinValid ->
            btnSelect.isEnabled = isPinValid

            if(!isPinValid) {
                txtErrorView.visibility = View.VISIBLE
                txtErrorView.text = activity?.getText(R.string.enter_pin_errortext)
            }
            else {
                txtErrorView.visibility = View.GONE
                txtErrorView.text = ""
            }
        })

        mViewModel.errorMessageData.observe(viewLifecycleOwner,{errorMessageData ->

            btnSelect.hideProgress(R.string.btn_txtselect)

            if(errorMessageData.description !== null){
                txtErrorView.text = errorMessageData.description
            }else if(errorMessageData.code == null){
                txtErrorView.text =   errorMessageData.error
            }else{
                txtErrorView.text = errorMessageData.code
            }

            txtErrorView.setTextColor(
                ContextCompat.getColor(
                    txtErrorView.context,
                    R.color.txt_error
                )
            )
        })

        mViewModel.wraperKeyResponse.observe(viewLifecycleOwner,{wraperKeyResponse ->

            mViewModel.pinChangeAPICall(MySingleton.panNumber,wraperKeyResponse.data?.wrappedKey.toString(),edtReEnterPin.text.toString());
        })

        mViewModel.pinChangeResponse.observe(viewLifecycleOwner,{pinChangeResponse ->

            if(pinChangeResponse.isSuccess == true)
                navigateToPinActiveSuccessScreen()
            else
                txtErrorView.visibility = View.VISIBLE
                txtErrorView.text = getString(R.string.enter_pin_errortext)
            txtErrorView.setTextColor(
                ContextCompat.getColor(
                    txtErrorView.context,
                    R.color.txt_error
                )
            )

            btnSelect.hideProgress(R.string.btn_txtselect)
        })
    }

    private fun navigateToPinActiveSuccessScreen() {
        // Get the support fragment manager instance
        val manager = activity?.supportFragmentManager

        val cardActivatedFragment = PinChangeSuccessFragment()
        val transaction = manager?.beginTransaction()
        transaction?.replace(R.id.fragmentMain,cardActivatedFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}
