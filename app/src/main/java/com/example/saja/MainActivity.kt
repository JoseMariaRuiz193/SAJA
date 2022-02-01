package com.example.saja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
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

        val tv: TextView = findViewById(R.id.price)
        tv.setText(currentprice.toString())


    }
}