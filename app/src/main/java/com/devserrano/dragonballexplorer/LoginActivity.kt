package com.devserrano.dragonballexplorer

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerText =  findViewById<TextView>(R.id.tvRegister)

        registerText.setOnClickListener {
            val intent = Intent(
                this@LoginActivity,
                RegisterActivity::class.java)

                startActivity(intent)

        }





    }
}