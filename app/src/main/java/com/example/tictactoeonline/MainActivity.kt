package com.example.tictactoeonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

var singleuser = false;
class MainActivity : AppCompatActivity() {
    lateinit var offline: Button
    lateinit var online: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        offline = findViewById(R.id.offline)
        online  = findViewById(R.id.online)
        offline.setOnClickListener{
            singleuser = true;
            startActivity(Intent(this, Offlineactivity:: class.java))
        }
        online.setOnClickListener {
            startActivity(Intent(this, Offlineactivity:: class.java))
        }
    }
}