package com.example.bestplace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var emailView : EditText
    lateinit var passwordView : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        emailView = findViewById(R.id.emailEditText)
        passwordView = findViewById(R.id.passwordEditText)


        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            signUp()
        }

        val logInButton = findViewById<Button>(R.id.logInButton)
        logInButton.setOnClickListener {
            logIn()
        }


      if (auth.currentUser !=null) {
           goToMenu()
      }

    }

    fun goToMenu() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    fun logIn() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

      //  if (email.isEmpty() || password.isEmpty()) {
        //    return
        //}

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.isSuccessful) {
                        Log.d("!!!", "Signed In")
                        goToMenu()
                    }
                }
            }
    }


    fun signUp() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

      //  if (email.isEmpty() || password.isEmpty()) {
        //    return
        //}

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showSignUpToast("Your account was created, you are now logged in.")
                    Log.d("!!!", "create success")

                   goToMenu()

                } else {
                    showSignUpToast("Error, please try again")
                    Log.d("!!!", "user not created ${task.exception}")
                }
            }
    }

    fun showSignUpToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }


}

