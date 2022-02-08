package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.TextView
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val apiClient = DefaultApi()
        ApiClient.apiKey["token"] = "sandbox_c7jgp8iad3i887nsdhgg"
        val json = apiClient.quote("TSLA")
        val currentprice = json.c
        val ticker = json.pc

        val setprice: TextView = findViewById(R.id.price)
        setprice.setText(currentprice.toString())

        val setticker: TextView = findViewById(R.id.ticker)
        setticker.setText(ticker.toString())

    }
    fun onClickInicio(view: View){
        irPantallaInicio()
    }

    fun irPantallaInicio() {
        val pantallaInicio = Intent(this, LoginActivity::class.java)
        startActivity(pantallaInicio)
    }
}