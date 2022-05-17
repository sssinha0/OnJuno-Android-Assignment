package com.qrcodescanner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class showResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)
        val sharedPrefFile="type"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val showdata=findViewById<TextView>(R.id.dataresult)
        val validtebtn=findViewById<Button>(R.id.validate)
        val showresult=findViewById<TextView>(R.id.showresut)
        val share=findViewById<ImageButton>(R.id.share)
        val resultdata=intent.getStringExtra("data")
        showdata.setText(intent.getStringExtra("data"))
        val sharedAgeValue = sharedPreferences.getInt("btntype", 0)
        var res:Boolean=true
        var flag=1;
        validtebtn.setOnClickListener {
            flag=0
            if (resultdata == null)
                res = false
            else if (sharedAgeValue.equals(1)) {
                if (resultdata.length < 25 || resultdata.length > 34) {
                    res = false
                } else if (resultdata?.get(0)
                        .toString() != "1" || resultdata.contains("0") || resultdata.contains("O")
                    || resultdata.contains("l") || resultdata.contains("I")
                ) {
                    res = false
                }
            } else {
                if (resultdata[0] == '0' && resultdata[1] == 'x') {

                    for (i in 2..resultdata.length - 1) {
                        var y=resultdata[i].toInt()
                        if((y>=48&&y<58)||(y<103&&y>=97)){
                            continue
                        }else{
                            res=false
                            break
                        }
                    }
                }else{
                    res=false
            }
            }
            if (res)
                showresult.setText("It is a valid Address")
            else {
                showresult.setText("Not a valid Address")
            }
        }
        share.setOnClickListener {
            if(flag==1){
                Toast.makeText(
                    applicationContext,
                    "Please Validte the address First", Toast.LENGTH_SHORT
                ).show()
            }
            else if(res){


                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, resultdata)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, "BTC/ETH Address")
                    startActivity(shareIntent)


            }else {
                Toast.makeText(
                    applicationContext,
                    "Error Not a Valid Address", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}