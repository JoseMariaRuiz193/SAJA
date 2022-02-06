package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class tuto1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuto1)

        val buttontuto1 = findViewById<Button>(R.id.buttontuto1)
        buttontuto1.setOnClickListener{
            val intent = Intent(this, tuto2::class.java)
            startActivity(intent)

        }

    }

}