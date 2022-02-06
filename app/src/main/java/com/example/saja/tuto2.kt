package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class tuto2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuto2)

        val buttontuto2 = findViewById<Button>(R.id.buttontuto2)
        buttontuto2.setOnClickListener{
            val intent = Intent(this, tuto3::class.java)
            startActivity(intent)
        }
    }
}