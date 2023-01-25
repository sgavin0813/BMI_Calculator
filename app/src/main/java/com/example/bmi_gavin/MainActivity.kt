package com.example.bmi_gavin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView:TextView = findViewById(R.id.resultText)
        val editWeight:EditText = findViewById(R.id.weight)
        val editHeightF:EditText = findViewById(R.id.heightFeet)
        val editHeightI:EditText = findViewById(R.id.heightInch)
        val btnCalc:Button = findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener(MyOnClickListener(editWeight,editHeightF,editHeightI, textView))
    }
}

class MyOnClickListener(private val editWeight:EditText,private val editHeightF:EditText,private val editHeightI:EditText,val textView:TextView) : View.OnClickListener {
    override fun onClick(v: View?) {
        val weight = (editWeight.text.toString()).toInt()
        val heightI = (editHeightI.text.toString()).toInt()
        val heightF2I = (editHeightF.text.toString()).toInt() * 12
        val heightInches  = heightF2I * heightI
        val bmi = (weight / (heightInches * heightInches)) * 703
        when {
            bmi < 18.5 -> {
                textView.text = "Underweight"
            }
            bmi < 25 -> {
                textView.text = "Normal weight"
            }
            bmi < 30 -> {
                textView.text = "Overweight"
            }
            else -> {
                textView.text = "Obese"
            }
        }

    }
}


