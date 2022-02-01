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
        val password1 : EditText = findViewById(R.id.password1)
        val username : EditText = findViewById(R.id.UsernameText)


            if (password.text.toString()!= "" && correo.text.toString() != "") {
                        if(password.text.toString()==password1.text.toString()){
                            db.collection("users").document(username.text.toString()).set(hashMapOf("email" to correo.text.toString(),"password" to password.text.toString()))
                            FirebaseAuth.getInstance()
                                .createUserWithEmailAndPassword(correo.text.toString(), password.text.toString())
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        db.collection("users").document(username.text.toString()).set(hashMapOf("email" to correo.text.toString(),"password" to password.text.toString()))
                                        val main= Intent(this, MainActivity::class.java)
                                        startActivity(main)
                                    } else {
                                        //Error
                                        showAlert()
                                    }
                                }
                        }else {
                            //Error
                            showAlertPasswordIguales()
                        }

            }else {
                //Error
                showAlertCamposVacios()
            }

    }
    fun onClickInicio(view: View){
        irPantallaInicio()
    }

    fun irPantallaInicio() {
        val pantallaInicio = Intent(this, LoginActivity::class.java)
        startActivity(pantallaInicio)
    }
    fun showAlertCamposVacios() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Los campos no pueden estar vacíos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    fun showAlertPasswordIguales() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Las contraseñas no coinciden")
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