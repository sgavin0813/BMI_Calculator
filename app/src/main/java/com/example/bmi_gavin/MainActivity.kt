package com.example.bmi_gavin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

lateinit var sharedPreference:SharedPreferences
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
        //initialize shared preferences
        sharedPreference = this.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)

        val resetBMI:Button = findViewById(R.id.resetBMI)
        resetBMI.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                editWeight.text.clear()
                editHeightI.text.clear()
                editHeightF.text.clear()
                result.text = "Results"
            }

        })
        val searchButton = findViewById<Button>(R.id.history)
        searchButton.setOnClickListener{
            val intent = Intent(this,History::class.java)
            startActivity(intent)
        }
    }
}

class MyOnClickListener(private val editWeight:EditText,private val editHeightF:EditText,private val editHeightI:EditText,val result:TextView) : View.OnClickListener {
    override fun onClick(v: View?) {
        var weight : Float
        var heightF : Float
        var heightInches : Float
        val bmiResult:String
        //next  4 lines are used to get value of current date in string format
        val outputFormat = DateTimeFormatter.ofPattern("MM-dd-yy", Locale.getDefault())
        val dateTime = LocalDateTime.now()
        //formatted date is string
        val formattedDate = dateTime.format(outputFormat)

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

        //value of bmi is calculated
        val bmi:Float = 703*(weight / (heightInchesTotal * heightInchesTotal))



        when {
            bmi < 18.5 -> {
                bmiResult = "Underweight"
                result.text = "Underweight"
            }
            bmi < 25 -> {
                bmiResult = "Normal weight"
                result.text = "Normal weight"
            }
            bmi < 30 -> {
                bmiResult = "Overweight"
                result.text = "Overweight"
            }
            bmi <34.9 -> {
                bmiResult = "Class I obese"
                result.text = "Class I obese"
            }
            bmi <39.9-> {
                bmiResult = "Class II obese"
                result.text = "Class II obese"
            }
            else -> {
                bmiResult = "Class III obese"
                result.text = "Class III obese"
            }

        }
        //code to dave data as shared preference
        val editor = sharedPreference.edit()
        editor.putString(formattedDate,("$bmi-$bmiResult"))
        editor.apply()
    }

}


