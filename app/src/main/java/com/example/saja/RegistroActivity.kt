package com.example.saja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegistroActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)
    }
    fun onClickdb(view: android.view.View){
        val correo : EditText = findViewById(R.id.email)
        val password : EditText = findViewById(R.id.password)


        if (password.text.toString()!= "" && correo.text.toString() != "") {

            db.collection("users").document(correo.text.toString()).set(hashMapOf("password" to password.text.toString()))

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(correo.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        db.collection("users").document(correo.text.toString()).set(hashMapOf("password" to password.text.toString()))
                        val main= Intent(this, MainActivity::class.java)
                        startActivity(main)
                    } else {
                        //Error
                        showAlert()
                    }

                }

        }else {
            //Error
            showAlert()
        }

    }
    fun onClickInicio(view: View){
        irPantallaInicio()
    }

    fun irPantallaInicio() {
        val pantallaInicio = Intent(this, InicioActivity::class.java)
        startActivity(pantallaInicio)
    }
    fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error con firebase")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}