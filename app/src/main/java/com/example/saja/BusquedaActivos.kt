package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.TextView
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient

class BusquedaActivos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_activos)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val apiClient = DefaultApi()
        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jdesaapl = apiClient.companyProfile2(symbol = "AAPL", isin = null, cusip = null)
        val desaapl = jdesaapl.exchange
        val setdesaapl: TextView = findViewById(R.id.mercado)
        setdesaapl.setText(desaapl)


        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jsonapple = apiClient.quote("AAPL")
        val currentpriceappledolar = jsonapple.c
        val redondearapple = "%.2f".format(currentpriceappledolar).toDouble()
        val setpriceapple: TextView = findViewById(R.id.textView6)
        setpriceapple.setText(redondearapple.toString()+" $")

        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jnomaapl = apiClient.companyProfile2(symbol = "AAPL", isin = null, cusip = null)
        val nombaapl = jnomaapl.name
        val setnomaapl: TextView = findViewById(R.id.textView5)
        setnomaapl.setText(nombaapl)

        val tickeraapl = jnomaapl.ticker
        val settickeraapl: TextView = findViewById(R.id.textView4)
        settickeraapl.setText(tickeraapl)

        val perapple = jsonapple.dp
        if (perapple != null) {
            if (perapple > 0) {
                val setperapple: TextView = findViewById(R.id.tslaperpos)
                setperapple.setText("▲ "+perapple.toString()+" %")

               val nosetpertsla: TextView = findViewById(R.id.tslaperneg)
                nosetpertsla.setText("")

            }
            if (perapple <= 0) {
                val setperapple: TextView = findViewById(R.id.tslaperneg)
                setperapple.setText("▼ "+perapple.toString()+" %")

                val nosetpertsla: TextView = findViewById(R.id.tslaperpos)
                nosetpertsla.setText("")
            }



        }


    }
    fun onClickInicio(view: View){
        irPantallahome()
    }

    fun irPantallahome() {
        val pantallaInicio = Intent(this, MainActivity::class.java)
        startActivity(pantallaInicio)
    }
}