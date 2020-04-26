package com.musheg_h.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.models.User
import com.musheg_h.myapplication.utils.UserType
import com.musheg_h.myapplication.viewmodel.UserEmailViewModel
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {


    private var userEmailViewModel: UserEmailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        userEmailViewModel = UserEmailViewModel(application)

        val intent: Intent = intent
        val username: String = intent.getStringExtra("username")
        val password: String = intent.getStringExtra("password")
        val email: String = intent.getStringExtra("email")
        val userType = intent.getSerializableExtra("userType") as UserType

        buttonVerify.setOnClickListener {
            val code = Integer.parseInt(emailsCode.text.toString())
            userEmailViewModel?.postRegisterUser(User(username, password, email, userType, code))
                ?.observe(this as LifecycleOwner, Observer {user->
                    if(user.email == null)
                    {
                        Toast.makeText(this,"The code isn't incorrect",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                       val intent : Intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
        }
    }
}
