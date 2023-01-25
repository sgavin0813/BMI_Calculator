package com.example.bmi_gavin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.view.View

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //used to connect kotlin/java to xml file
        // R.layout.activity_main -> reference to a file named "activity_main"
        setContentView(R.layout.activity_main)

        //setContentView is where we actually start coding part

        val textView:TextView = findViewById(R.id.resultText)
        //findViewById() is a method ->  used to retrieve a view in an Activity by its unique identifier
        //R.id is subclass of 'R' and R.id.weight is reference to a view with id weight.
        val editWeight:EditText = findViewById(R.id.weight)
        val editHeightF:EditText = findViewById(R.id.heightFeet)
        val editHeightI:EditText = findViewById(R.id.heightInch)
        val btnCalc:Button = findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener(MyOnClickListener(editWeight,editHeightF,editHeightI, textView))
    }
}

class MyOnClickListener(private val editWeight:EditText,private val editHeightF:EditText,private val editHeightI:EditText,val textView:TextView) : View.OnClickListener {
    override fun onClick(v: View?) {
        val weight = (editWeight.text.toString()).toDouble()
        val heightI = (editHeightI.text.toString()).toDouble()
        val heightF = (editHeightF.text.toString()).toDouble()
        val heightInches  = (heightF * 12) +  heightI
        val bmi = (weight / (heightInches * heightInches)) * 703

            when {
                bmi < 18.5 -> textView.text = "Skinny Bitch"
                bmi in 18.5..24.9 -> textView.text = "Good Job"
                bmi in 25.0..29.9 -> textView.text = "Fat ass"
                else -> textView.text = "Get a Gym Membership"
            }



        }
}


