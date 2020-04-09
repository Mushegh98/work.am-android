package com.musheg_h.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.Repository
import kotlinx.android.synthetic.main.activity_newscreen.*

class NewScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newscreen)

        val token = intent.getStringExtra("token")

        var response : String
        button.setOnClickListener {
           Repository.postHelloUser(token!!).observe(this as LifecycleOwner , Observer {
               response = it.token
               text.text = response
           })
        }

    }

}