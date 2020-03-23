package com.musheg_h.myapplication.activities

import android.content.Context
import android.os.Bundle
import android.service.autofill.Validators.and
import android.util.Patterns
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {


    private var animation: Animation? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(bgHeader)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        animation = AnimationUtils.loadAnimation(
            this,
            R.anim.uptodowndiagonal
        )
        rlayout?.animation = animation

        email_text.requestFocus()


        buttonSingnup.setOnClickListener {
            if (validPassword() and validUsername() and validateEmail() and isNetworkAvailable(this)) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                finish()
            }
            if (!isNetworkAvailable(this)) {
                Toast.makeText(this, resources.getString(R.string.turnInternet), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun validPassword(): Boolean {
        val inputPassword = password.text.toString()



        if (password.text.toString() == confirmPassword.text.toString()) {

            for (i in inputPassword.indices) {
                if (inputPassword[i] == ' ') {
                    password.error = "Password can't be have space symbol"
                    confirmPassword.error = "Password can't be have space symbol"
                    return false
                }
            }

            if (password.text.toString().isEmpty() || password.text.toString().isBlank()) {
                password.error = resources.getString(R.string.cantEmpty)

                confirmPassword.error = resources.getString(R.string.cantEmpty)

                return false
            } else {
                return true
            }

        } else {
            password.error = "Passwords are not equals"
            confirmPassword.error = "Passwords are not equals"
            return false
        }

    }

    private fun validUsername(): Boolean {
        val inputUsername = username.text.toString()

        if (inputUsername.length < 6) {
            username.error = "Please write minimum 6 symbol"
            return false
        } else if (inputUsername.length >= 6 || inputUsername.isBlank()) {
            for (i in inputUsername.indices) {
                if (inputUsername[i] == ' ') {
                    username.error = "Username can't be have space symbol"
                    return false
                }
            }
        }
        return true
    }


    private fun validateEmail(): Boolean {
        val emailInput = email_text.text.toString().trim()
        return if (emailInput.isEmpty()) {
            email_text.error = resources.getString(R.string.cantEmpty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email_text.error = resources.getString(R.string.validEmail)
            false
        } else {
            true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}