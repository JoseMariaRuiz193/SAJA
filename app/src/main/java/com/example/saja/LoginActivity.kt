package com.example.saja

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Firebase tutorial")
        analytics.logEvent("InitScreen", bundle)

        //login de google - inicio

        checkSession()


        //Google

        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
        requestIdToken(getString(R.string.default_web_client_id2)).requestEmail().build()

        val gClient= GoogleSignIn.getClient(this, googleConf)

        val registerButton : ImageButton = findViewById(R.id.imageButton2);
        registerButton.setOnClickListener {
            gClient.signOut()
            val intent = gClient.signInIntent
            startActivityForResult(intent,100)
        }


    }
    private fun checkSession(){
        val preferences = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE) //RECOGEMOS EL EMAIL
        val email = preferences.getString("email", null)
        if(email != null) {
            val main= Intent(this, MainActivity::class.java)
            startActivity(main)
        }

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
    fun onClickRegistro(view: View){
        irPantallaRegistro()
    }

    fun irPantallaRegistro() {
        val pantallaRegistro = Intent(this, RegistroActivity::class.java)
        startActivity(pantallaRegistro)
    }
    private fun showError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error con Firebase")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google Sign In was succesful, authenticate with firebase
                val account = task.getResult(ApiException::class.java)!!
                if(account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            //Navegamos a la siguiente pantalla
                            val main= Intent(this, MainActivity::class.java)
                            startActivity(main)
                        } else {
                            //Error
                            showError()
                        }
                    }
                }
            } catch (e: ApiException) {
                //Google Sign In failed
                showError()
            }
        }
    }
}
