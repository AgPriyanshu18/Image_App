package com.example.imageapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imageapp.R
import com.example.imageapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    var binding: ActivitySecondBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.button?.setOnClickListener {
            val text = binding?.text?.text.toString()
            val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply{
                putString("STRING_KEY", text)
            }.apply()
        }

        //show text in textview textshow from shared preferences, if there is no text in shared preferences, show "No text"
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", "No text")
        binding?.textshow?.text = savedString



    }
}