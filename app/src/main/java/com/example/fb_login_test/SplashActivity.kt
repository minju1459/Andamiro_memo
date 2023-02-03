package com.example.fb_login_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = Firebase.auth

        try {

            Log.d("SPLASH", auth.currentUser!!.uid)
            Toast.makeText(this, "이미 로그인된 상태입니다.", Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2000)

        } catch (e: Exception) {
            Log.d("SPLASH", "로그인 아직 안되어있음.")

            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Log.d("MainActivity", user!!.uid)
                        Log.d("login", "로그인 성공")
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                        Log.d("login", "로그인 실패")
                    }
                }
        }
    }
}
