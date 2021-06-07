package com.example.tiptime

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }

    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {

        val stringInTextField = binding.plainTextInput.text.toString()
        if(stringInTextField.isEmpty()){
            binding.tipResult.text = ""
            return
        }
        val cost = stringInTextField.toDoubleOrNull()

        val selectedOption = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when( selectedOption ){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost!!

        if(binding.roundUpSwitch.isChecked)
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


    }
}