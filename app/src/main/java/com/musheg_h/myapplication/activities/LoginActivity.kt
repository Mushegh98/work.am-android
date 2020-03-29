package com.musheg_h.myapplication.activities

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.models.UserLogin
import com.musheg_h.myapplication.viewmodel.UserLoginViewModel
import kotlinx.android.synthetic.main.activity_login_layout.*

class LoginActivity : AppCompatActivity() {

    var userLoginViewModel : UserLoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_layout)
        userNameLogin.requestFocus()

        userLoginViewModel = UserLoginViewModel(application)


        btRegister.setOnClickListener {
            if (it==btRegister){
                val intent  = Intent(applicationContext,
                    RegisterActivity::class.java)
               val pairs : android.util.Pair<View,String> = android.util.Pair<View,String>(tvLogin,"tvLogin")
               val activityOptions : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,pairs)
                startActivity(intent,activityOptions.toBundle())
            }
        }

        button_login.setOnClickListener {
            val userName = userNameLogin.text.toString()
            val password = userPasswordlogin.text.toString()
            var user = UserLogin(username = userName , password = password)
            userLoginViewModel?.postLoginUser(user)
        }

    }


}