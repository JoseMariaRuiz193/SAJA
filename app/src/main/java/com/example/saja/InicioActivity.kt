package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
    }
    fun onClickRegistro(view: View){
        irPantallaRegistro()
    }

    fun irPantallaRegistro() {
        val pantallaRegistro = Intent(this, RegistroActivity::class.java)
        startActivity(pantallaRegistro)
    }

    fun onClickLogin(view: View){
        irPantallaLogin()
    }

    fun irPantallaLogin() {
        val pantallaLogin = Intent(this, LoginActivity::class.java)
        startActivity(pantallaLogin)
    }
}