package com.example.fb_login_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val btn=findViewById<Button>(R.id.emailbtn)
        btn.setOnClickListener{

            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        Log.d("MainActivity",user!!.uid)

                    } else {

                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                    }
                }

        }
    }
}