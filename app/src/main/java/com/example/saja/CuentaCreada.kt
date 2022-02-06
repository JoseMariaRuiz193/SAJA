package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CuentaCreada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta_creada)

        val buttontuto1 = findViewById<Button>(R.id.Tutorial)
        buttontuto1.setOnClickListener{
            val intent = Intent(this, tuto1::class.java)
            startActivity(intent)
        }
    }
}
