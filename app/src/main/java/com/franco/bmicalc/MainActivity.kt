package com.franco.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener{
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get the results in 2 dp
                val bmiRoundedOff = String.format("%.2f", bmi).toFloat()
                displayResults(bmiRoundedOff)
            }

        }
    }

//    function to validate user's inputs if not left blank
    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResults(bmi: Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultsDescription = findViewById<TextView>(R.id.tvResults)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 16.5 to 24.9)"

//        code for the results view
        var resultText = ""
        var color = 0

        when{
            bmi<18.50 -> {
                resultText = "You are underweight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99 -> {
                resultText = "You are Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "You are overweight"
            }
            bmi > 29.99 -> {
                resultText = "You are obese"
                color = R.color.obese
            }
        }

        resultsDescription.setTextColor(ContextCompat.getColor(this,color))
        resultsDescription.text = resultText
    }
}