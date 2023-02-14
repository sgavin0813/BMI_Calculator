package com.example.bmi_gavin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.view.View
import java.lang.Boolean

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //used to connect kotlin/java to xml file
        // R.layout.activity_main -> reference to a file named "activity_main"
        setContentView(R.layout.activity_main)

        //setContentView is where we actually start coding part
        
        val result:TextView = findViewById(R.id.resultText)
        //findViewById() is a method ->  used to retrieve a view in an Activity by its unique identifier
        //R.id is subclass of 'R' and R.id.weight is reference to a view with id weight.
        val editWeight:EditText = findViewById(R.id.weight)
        val editHeightF:EditText = findViewById(R.id.heightFeet)
        val editHeightI:EditText = findViewById(R.id.heightInch)
        val btnCalc:Button = findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener(MyOnClickListener(editWeight,editHeightF,editHeightI, result))

        val resetBMI:Button = findViewById(R.id.resetBMI)
        resetBMI.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                editWeight.text.clear()
                editHeightI.text.clear()
                editHeightF.text.clear()
                result.text = "Results"
            }

        })

    }
}

class MyOnClickListener(private val editWeight:EditText,private val editHeightF:EditText,private val editHeightI:EditText,val result:TextView) : View.OnClickListener {
    override fun onClick(v: View?) {
        var weight : Float
        var heightI : Float
        var heightF : Float
        var heightInches : Float

        if (editWeight.text.toString().isEmpty()){
            weight = 1.0F
        }
        else{
            weight = (editWeight.text.toString()).toFloat()
        }

        if ((editHeightI.text.toString()).isEmpty()) {
            heightInches = 1.0F
        }
        else{
            heightInches = (editHeightI.text.toString()).toFloat()
        }

        if ((editHeightF.text.toString()).isEmpty()){
            heightF = 1.0f
        }
        else{
            heightF = (editHeightF.text.toString()).toFloat()
        }


        val heightInchesTotal = heightInches + (heightF * 12)


        val bmi:Float = 703*(weight / (heightInchesTotal * heightInchesTotal))

        when {

            bmi < 16 -> {
                result.text = "Severely Underweight"
            }
            bmi < 18.5 -> {
                result.text = "Underweight"
            }
            bmi < 25 -> {
                result.text = "Normal weight"
            }
            bmi < 30 -> {
                result.text = " Overweight"
            }
            bmi <34.9 -> {
                result.text = "Class I obese"
            }
            bmi <39.9-> {
                result.text = "Class II obese"
            }
            else -> {
                result.text = "Class III obese"
            }

        }
    }
}


