package com.coop.pinchangewidget.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.util.*
import com.nimbusds.jose.Payload
import com.nimbusds.jose.JWEHeader
import com.nimbusds.jose.JWEObject
import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWEAlgorithm
import com.nimbusds.jose.crypto.AESEncrypter
import android.util.Base64
import androidx.annotation.RequiresApi

object  AppUtils {

    var keyGenerated: String = "";

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }
    public fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /*
    * generate random string which satisfies
    * Pattern: ^[A-Za-z0-9+/]{42}[AEIMQUYcgkosw048]=$
    */
    fun generateWraperRandomKey(): String {

        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/" // 9
        val secondSet = "AEIMQUYcgkosw048"
        val n = alphabet.length
        var result = String()
        val r = Random()
        for (i in 0 until 42)
            result += alphabet[r.nextInt(n)]

        result += secondSet[r.nextInt(secondSet.length)];

        keyGenerated = "$result="
        return keyGenerated
    }

    /*
    * encrypt the input value using
    * algorithm : A256GCMKW
    * encryption : A256GCM
    *
    * 256 bit symmetric key using randomKey generated at  generateWraperRandomKey()
    * */

   @RequiresApi(Build.VERSION_CODES.M)
   fun generateKeyEncryptedBase64String(inputText: String): String {

        val decodedByteArray = Base64.decode(keyGenerated, Base64.DEFAULT)

        // Create the header
        val header = JWEHeader(JWEAlgorithm.A256GCMKW, EncryptionMethod.A256GCM)
        val payload = Payload(inputText)

        // Create the JWE object and encrypt it
        var jweObject = JWEObject(header, payload)
        jweObject.encrypt(AESEncrypter(decodedByteArray))

        // Serialise to compact JOSE form...
        val jweString = jweObject.serialize()

        return jweString;
    }
}