package com.example.myapplication.ui.card

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Context
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
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.EnterpinFragmentBinding
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.cardactivation.viewmodel.CardActivationViewModel
import com.example.myapplication.utils.AppUtils
import com.example.myapplication.utils.AppUtils.hideKeyboard


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
                    btnActivate.isEnabled = true
                    mViewModel.validateAccountNumber(edt1.text.toString(),edt2.text.toString(),edt3.text.toString(),edt4.text.toString())
                }
            }
        })
        mViewModel.accNumberValidate.observe(viewLifecycleOwner, Observer<Boolean> { isAccNumberValid ->
            // Update the UI
            //Log.i("isAccNumberValid", isAccNumberValid.toString())
            txtAccNumMsg.visibility = View.VISIBLE
            if(isAccNumberValid){
                txtAccNumMsg.text = getString(R.string.accnum_valid_success)
                txtAccNumMsg.setTextColor(getColor(txtAccNumMsg.context,R.color.card_number_valid_color))
            }else{
                txtAccNumMsg.text = getString(R.string.accnum_invalid_error)
                txtAccNumMsg.setTextColor(getColor(txtAccNumMsg.context,R.color.card_number_invalid_color))
            }
        })

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