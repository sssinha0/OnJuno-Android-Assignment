package com.qrcodescanner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Selectbtceth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectbtceth)
        val btc = findViewById<Button>(R.id.btc)
        val eth=findViewById<Button>(R.id.eth)
        val sharedPrefFile="type"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        btc.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putInt("btntype", 1)
            editor.apply()
            editor.commit()
            val intent = Intent(this@Selectbtceth,MainActivity::class.java)
            startActivity(intent)
        }
        eth.setOnClickListener{
            val editor=sharedPreferences.edit()
            editor.putInt("btntype", 2)
            editor.apply()
            editor.commit()
            val intent = Intent(this@Selectbtceth,MainActivity::class.java)
            startActivity(intent)
        }
    }
}