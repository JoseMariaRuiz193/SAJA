package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import com.squareup.picasso.Picasso


class BusquedaActivos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_activos)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        if(intent.getExtras() != null){

            //Pasar valor de la activity Main
            val value = intent.getExtras()?.getString("activo", "")
            val apiClient = DefaultApi()

            //Cargar logo en el imageView
            ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
            val jsonactivo = apiClient.companyProfile2(symbol = value.toString(), isin = null, cusip = null)
            val image = findViewById<ImageView>(R.id.logo)
            Picasso.get().load(jsonactivo.logo).noFade().into(image)

            //Precio del activo
            ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
            val precioactivo = apiClient.quote(value.toString())
            val currentpriceappledolar = precioactivo.c
            val redondearactivo = "%.2f".format(currentpriceappledolar).toDouble()
            val setpriceapple: TextView = findViewById(R.id.textView6)
            setpriceapple.setText(redondearactivo.toString()+" $")

            //Nombre del activo
            ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
            val nombreactivo = apiClient.companyProfile2(symbol = value.toString(), isin = null, cusip = null)
            val nomb = nombreactivo.name
            val setnom: TextView = findViewById(R.id.textView5)
            setnom.setText(nomb)

            //Ticker del activo
            val tickeraapl = nombreactivo.ticker
            val setticker: TextView = findViewById(R.id.textView4)
            setticker.setText(tickeraapl)

            //Cambio de precio del activo
            val peractivo = precioactivo.dp
            if (peractivo != null) {
                if (peractivo > 0) {
                    val setperapple: TextView = findViewById(R.id.tslaperpos)
                    setperapple.setText("▲ "+peractivo.toString()+" %")

                    val nosetpertsla: TextView = findViewById(R.id.tslaperneg)
                    nosetpertsla.setText("")

                }
                if (peractivo <= 0) {
                    val setperapple: TextView = findViewById(R.id.tslaperneg)
                    setperapple.setText("▼ "+peractivo.toString()+" %")

                    val nosetpertsla: TextView = findViewById(R.id.tslaperpos)
                    nosetpertsla.setText("")
                }

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