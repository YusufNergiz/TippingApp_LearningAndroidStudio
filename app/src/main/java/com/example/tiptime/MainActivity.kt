package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTipButton.setOnClickListener{ calculateTip() }
    }
    fun calculateTip() {

        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            return
        }

        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.amazing_button -> 0.20
            R.id.good_button -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked

        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}