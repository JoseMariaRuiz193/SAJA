package com.example.saja

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import java.lang.System.load

class MainActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val apiClient = DefaultApi()

        //Precio Tesla
        ApiClient.apiKey["token"] = "sandbox_c84l5qaad3i9u79habjg"
        val jsontesla = apiClient.quote("TSLA")
        val currentpricetsladolar = jsontesla.c
        val redondeartsla = "%.2f".format(currentpricetsladolar).toDouble()
        val setpricetsla: TextView = findViewById(R.id.teslaprice)
        setpricetsla.setText(redondeartsla.toString()+" $")

        //Nombre Tesla
        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jnomtsla = apiClient.companyProfile2(symbol = "TSLA", isin = null, cusip = null)
        val nombtsla = jnomtsla.name
        val setnomtsla: TextView = findViewById(R.id.teslanom)
        setnomtsla.setText(nombtsla)

        //Ticker Tesla
        val tickertsla = jnomtsla.ticker
        val settickertsla: TextView = findViewById(R.id.teslaticker)
        settickertsla.setText(tickertsla)

        //Logo Tesla
        val imagetesla = findViewById<ImageView>(R.id.logotesla)
        Picasso.get().load(jnomtsla.logo).noFade().into(imagetesla)

        //%Cambio Tesla
        val pertsla = jsontesla.dp
        if (pertsla != null) {
            if (pertsla > 0) {
                val setpertsla: TextView = findViewById(R.id.tslaperpos)
                setpertsla.setText("▲ "+pertsla.toString()+" %")

                val nosetpertsla: TextView = findViewById(R.id.tslaperneg)
                nosetpertsla.setText("")
            }
            if (pertsla <= 0) {
                val setpertsla: TextView = findViewById(R.id.tslaperneg)
                setpertsla.setText("▼ "+pertsla.toString()+" %")

                val nosetpertsla: TextView = findViewById(R.id.tslaperpos)
                nosetpertsla.setText("")
            }

        }

        //Precio Intel
        ApiClient.apiKey["token"] = "sandbox_c84l5qaad3i9u79habjg"
        val jsonintel = apiClient.quote("INTC")
        val currentpriceappledolar = jsonintel.c
        val redondearintel = "%.2f".format(currentpriceappledolar).toDouble()
        val setpriceintel: TextView = findViewById(R.id.appleprice)
        setpriceintel.setText(redondearintel.toString()+" $")

        //Nombre Intel
        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jnomintel  = apiClient.companyProfile2(symbol = "INTC", isin = null, cusip = null)
        val nombintel  = jnomintel .name
        val setnomintel : TextView = findViewById(R.id.applenom)
        setnomintel .setText(nombintel )

        //Ticker Intel
        val tickeraapl = jnomintel .ticker
        val settickeraapl: TextView = findViewById(R.id.appleticker)
        settickeraapl.setText(tickeraapl)

        //Logo Intel
        val imageintel = findViewById<ImageView>(R.id.logointel)
        Picasso.get().load(jnomintel.logo).noFade().into(imageintel)

        //%Cambio Intel
        val perapple = jsonintel.dp
        if (perapple != null) {
            if (perapple > 0) {
                val setperapple: TextView = findViewById(R.id.appleperpos)
                setperapple.setText("▲ "+perapple.toString()+" %")

                val nosetperapple: TextView = findViewById(R.id.appleperneg)
                nosetperapple.setText("")
            }
            if (perapple <= 0) {
                val setperapple: TextView = findViewById(R.id.appleperneg)
                setperapple.setText("▼ "+perapple.toString()+" %")

                val nosetperapple: TextView = findViewById(R.id.appleperpos)
                nosetperapple.setText("")
            }

        }

        //Precio Paypal
        ApiClient.apiKey["token"] = "sandbox_c84l5qaad3i9u79habjg"
        val jsonpypl = apiClient.quote("PYPL")
        val currentpricepypldolar = jsonpypl.c
        val redondearpypl = "%.2f".format(currentpricepypldolar).toDouble()
        val setpricepypl: TextView = findViewById(R.id.pyplprice)
        setpricepypl.setText(redondearpypl.toString()+" $")

        //Nombre Paypal
        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jnompypl = apiClient.companyProfile2(symbol = "PYPL", isin = null, cusip = null)
        val nombpypl = jnompypl.name?.substring(0,6)
        val setnompypl: TextView = findViewById(R.id.pyplnom)
        setnompypl.setText(nombpypl)

        //Ticker Paypal
        val tickerpypl = jnompypl.ticker
        val settickerpypl: TextView = findViewById(R.id.pyplticker)
        settickerpypl.setText(tickerpypl)

        //Logo Paypal
        val imagepaypal = findViewById<ImageView>(R.id.logopypl)
        Picasso.get().load(jnompypl.logo).noFade().into(imagepaypal)

        //%Cambio Paypal
        val perpypl = jsonpypl.dp
        if (perpypl != null) {
            if (perpypl > 0) {
                val setperpypl: TextView = findViewById(R.id.pyplperpos)
                setperpypl.setText("▲ "+perpypl.toString()+" %")

                val nosetperpypl: TextView = findViewById(R.id.pyplperneg)
                nosetperpypl.setText("")
            }
            if (perpypl <= 0) {
                val setperpypl: TextView = findViewById(R.id.pyplperneg)
                setperpypl.setText("▼ "+perpypl.toString()+" %")

                val nosetperpypl: TextView = findViewById(R.id.pyplperpos)
                nosetperpypl.setText("")
            }

        }

        //Precio Amazon
        ApiClient.apiKey["token"] = "sandbox_c84l5qaad3i9u79habjg"
        val jsonamzn = apiClient.quote("AMZN")
        val currentpriceamzndolar = jsonamzn.c
        val redondearamzn = "%.2f".format(currentpriceamzndolar).toDouble()
        val setpriceamzn: TextView = findViewById(R.id.amznprice)
        setpriceamzn.setText(redondearamzn.toString()+" $")

        //Nombre Amazon
        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jnomamzn = apiClient.companyProfile2(symbol = "AMZN", isin = null, cusip = null)
        val nombamzn = jnomamzn.name?.substring(0,6)
        val setnomamzn: TextView = findViewById(R.id.amznnom)
        setnomamzn.setText(nombamzn)

        //Ticker Amazon
        val tickeramzn = jnomamzn.ticker
        val settickeramzn: TextView = findViewById(R.id.amznticker)
        settickeramzn.setText(tickeramzn)

        //Logo Amazon
        val imageamazon = findViewById<ImageView>(R.id.logoamzn)
        Picasso.get().load(jnomamzn.logo).noFade().into(imageamazon)

        //%Cambio Amazon
        val peramzn = jsonamzn.dp
        if (peramzn != null) {
            if (peramzn > 0) {
                val setperamzn: TextView = findViewById(R.id.amznperpos)
                setperamzn.setText("▲ "+peramzn.toString()+" %")

                val nosetperamzn: TextView = findViewById(R.id.amznperneg)
                nosetperamzn.setText("")
            }
            if (peramzn <= 0) {
                val setperamzn: TextView = findViewById(R.id.amznperneg)
                setperamzn.setText("▼ "+peramzn.toString()+" %")

                val nosetperamzn: TextView = findViewById(R.id.amznperpos)
                nosetperamzn.setText("")
            }

        }

        //Precio Microsoft
        ApiClient.apiKey["token"] = "sandbox_c84l5qaad3i9u79habjg"
        val jsonmsft = apiClient.quote("MSFT")
        val currentpricemsftdolar = jsonmsft.c
        val redondearmsft = "%.2f".format(currentpricemsftdolar).toDouble()
        val setpricemsft: TextView = findViewById(R.id.msftprice)
        setpricemsft.setText(redondearmsft.toString()+" $")

        //Nombre Microsoft
        ApiClient.apiKey["token"] = "c81p8fiad3i8p98ipb1g"
        val jnommsft = apiClient.companyProfile2(symbol = "MSFT", isin = null, cusip = null)
        val nombmsft = jnommsft.name?.substring(0,9)
        val setnommsft: TextView = findViewById(R.id.msftnom)
        setnommsft.setText(nombmsft)

        //Ticker Microsoft
        val tickermsft = jnommsft.ticker
        val settickermsft: TextView = findViewById(R.id.msftticker)
        settickermsft.setText(tickermsft)

        //Logo Amazon
        val imageamsft = findViewById<ImageView>(R.id.logomsft)
        Picasso.get().load(jnommsft.logo).noFade().into(imageamsft)

        //%Cambio Microsoft
        val permsft = jsonmsft.dp
        if (permsft != null) {
            if (permsft > 0) {
                val setpermsft: TextView = findViewById(R.id.msftperpos)
                setpermsft.setText("▲ "+permsft.toString()+" %")

                val nosetpermsft: TextView = findViewById(R.id.msftperneg)
                nosetpermsft.setText("")
            }
            if (permsft <= 0) {
                val setpermsft: TextView = findViewById(R.id.msftperneg)
                setpermsft.setText("▼ "+permsft.toString()+" %")

                val nosetpermsft: TextView = findViewById(R.id.msftperpos)
                nosetpermsft.setText("")
            }

        }


    }
    fun onClickInicio(view: View){
        irPantallaInicio()
    }

    fun irPantallaInicio() {
        val pantallaInicio = Intent(this, LoginActivity::class.java)
        startActivity(pantallaInicio)
    }

    fun buscar(view : View){
        val a : TextView = findViewById(R.id.editTextTextPersonName2)
        var atext = a.text.toString()
        val intent = Intent(this, BusquedaActivos::class.java)
        intent.putExtra("activo", atext )

        startActivity(intent)
    }

    fun logout(view : View){
        FirebaseAuth.getInstance().signOut();
        val pantallaInicio = Intent(this, LoginActivity::class.java)
        startActivity(pantallaInicio)
    }
}

