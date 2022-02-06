package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class tuto3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuto3)

        val buttontuto3 = findViewById<Button>(R.id.buttontuto3)
        buttontuto3.setOnClickListener{
            val intent = Intent(this, pantallaPrincipal::class.java)
            startActivity(intent)

        }

    }
}