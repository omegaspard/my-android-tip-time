package com.omega.tipTime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.omega.tipTime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            var costOfService = binding.costOfService.text.toString().toDoubleOrNull()

            if (costOfService == null) {
                binding.tipResult.text = ""
                costOfService = 0.0
            }

            val tip = when(binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                R.id.option_fifteen_percent -> 0.15
                else -> 0.10
            }

            val calculateTip = calculateTip(costOfService, tip,  binding.roundUpSwitch.isChecked)

            val formattedTip = NumberFormat.getCurrencyInstance().format(calculateTip)

            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        }
    }

    private fun calculateTip(costOfService: Double, tip: Double, roundUp:Boolean): Double {
        val tipAmount = costOfService * tip
        return if (roundUp) ceil(tipAmount) else tipAmount
    }
}