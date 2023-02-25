package com.example.bmi_gavin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        sharedPreference = getSharedPreferences("mySharedPreferences",Context.MODE_PRIVATE)
        val dateSearch = findViewById<EditText>(R.id.dateSearch)
        val searchText = findViewById<Button>(R.id.searchBMI)
        val bmiFound = findViewById<TextView>(R.id.bmiFound)
        val weightFound = findViewById<TextView>(R.id.weightFound)


        searchText.setOnClickListener{
            val dateKey = dateSearch.text.toString()
            val stringBMINWeight = sharedPreference.getString(dateKey,"No data - no data")
            val parts = stringBMINWeight?.split("-")
            bmiFound.text = parts?.get(0) ?: "No data"
            weightFound.text = parts?.get(1) ?: "No data"

        }
    }
}