package com.example.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.cardactivation.data.ClientData
import com.example.myapplication.ui.card.CardActivity
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnCardActivate = binding.buttonFirst
        val btnPinChange = binding.buttonSecond

        btnCardActivate.setOnClickListener {
            val clientData = ClientData()
            clientData.setName("Card Activation")
            clientData.setHeader("xxxx")

            val bundle = Bundle()
            bundle.putSerializable("client_data", clientData)
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("brandName","Chase")
            intent.putExtras(bundle)
            startActivity(intent)


          /*  val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    //  you will get result here in result.data
                    Toast.makeText(this,"success", Toast.LENGTH_LONG).show()
                }

            }
            startForResult.launch(Intent(this, CardActivity::class.java))
*/

        }



    }




}