package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun onClickLoginn(view: View){
        //Obtener texto de email y pass box
        val emailllogin : EditText = findViewById(R.id.correoLogin)
        val passslogin : EditText = findViewById(R.id.passLogin)

        //Ver que no nulos
        if(emailllogin.text.isNotEmpty() && passslogin.text.isNotEmpty()){
            var emailLoginString = emailllogin.text.toString()
            var passLoginString = passslogin.text.toString()

            try {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLoginString,passLoginString)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            val main= Intent(this, MainActivity::class.java)
                            startActivity(main)

                        }
                        else{
                            showAlert()
                        }
                    }

            }catch (ex: Exception){

                showAlert()
            }
        }else{
            showAlertCamposVacios()
        }
    }

    fun forgotPassword(view: View) {
        startActivity(
            Intent(
                this,
                ForgotPasswordActivity::class.java
            )
        )
    }

    fun showAlertCamposVacios() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Los campos no pueden estar vacíos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error on firebase")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}