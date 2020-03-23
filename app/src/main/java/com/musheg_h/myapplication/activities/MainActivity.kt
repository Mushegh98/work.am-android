package com.musheg_h.myapplication.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bgone.animate().scaleX(2f).scaleY(2f).setDuration(5000).start()

        btnget.setOnClickListener {
            if(isNetworkAvailable(this))
            {
                val a = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(a)
            }
            else
            {
                Toast.makeText(this,resources.getString(R.string.turnInternet),Toast.LENGTH_LONG).show()
            }
        }
    }
}
