package com.musheg_h.myapplication.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.models.EmailDTO
import com.musheg_h.myapplication.models.User
import com.musheg_h.myapplication.utils.UserType
import com.musheg_h.myapplication.utils.isNetworkAvailable
import com.musheg_h.myapplication.viewmodel.UserRegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import java.io.FileNotFoundException
import java.io.InputStream


class RegisterActivity : AppCompatActivity() {

    private var userViewModel: UserRegisterViewModel? = null
    private var animation: Animation? = null
    private var RESULT_LOAD_IMG: Int = 123
    private var imageFlag = false
    private var emailFlag = true


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_LOAD_IMG) {
                try {
                    val imageUri: Uri? = data?.data
                    val imageStream: InputStream? = contentResolver.openInputStream(imageUri!!)
                    val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
                    val d: Drawable = BitmapDrawable(resources, selectedImage)
                    uploadButtonImage.text = ""
                    uploadButtonImage.background = d
                    imageFlag = true
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userViewModel = UserRegisterViewModel(application)

        setSupportActionBar(bgHeader)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        animation = AnimationUtils.loadAnimation(
            this,
            R.anim.uptodowndiagonal
        )
        rlayout?.animation = animation

        email_text.requestFocus()


        button_group_per_or_comp.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.id_person) {
                    usernameTitle.text = getString(R.string.userName)
                    uploadButtonImage.visibility = View.GONE
                } else if (checkedId == R.id.id_company) {
                    usernameTitle.text = getString(R.string.companyName)
                    uploadButtonImage.visibility = View.VISIBLE
                }
            })

        uploadButtonImage.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
        }



        buttonSingnup.setOnClickListener {

            val isGroupButton: Boolean = button_group_per_or_comp.checkedRadioButtonId != -1

            if (validPassword() and validUsername() and validateEmail() and isNetworkAvailable(this) and isGroupButton) {

                var status: UserType = UserType.PERSON

                if (button_group_per_or_comp.checkedRadioButtonId == R.id.id_person) {
                    status = UserType.PERSON
                } else if (button_group_per_or_comp.checkedRadioButtonId == R.id.id_company) {
                    status = UserType.COMPANY
                    val statusImage = imageValidation()
                    if (!statusImage) {
                        return@setOnClickListener
                    }
                }


                val inputPassword = password.text.toString()
                val inputUsername = username.text.toString()
                val emailInput = email_text.text.toString().trim()
                val user = User(inputUsername, inputPassword, emailInput, status,0)
               // userViewModel?.postRegisterUser(user)
                val email : EmailDTO = EmailDTO(emailInput,inputUsername)
                userViewModel?.postEmail(email)?.observe(this as LifecycleOwner, Observer { emailDTO->
                    if(emailDTO.email == null)
                    {
                        Toast.makeText(this,"Email or username are exist",Toast.LENGTH_SHORT).show()
                        emailFlag =false
                        //TODO 2 hat trichq
                        return@Observer
                    }
                })
                //Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                val intent = Intent(baseContext, VerificationActivity::class.java)
                intent.putExtra("username", inputUsername)
                intent.putExtra("password",inputPassword)
                intent.putExtra("email", emailInput)
                intent.putExtra("userType",status)


                startActivity(intent)
                finish()
            }

            if (!isGroupButton) {
                Toast.makeText(
                    this,
                    "Select Person or Company",
                    Toast.LENGTH_LONG
                )
                    .show()
                return@setOnClickListener
            }

            if (!isNetworkAvailable(this)) {
                Toast.makeText(
                    this,
                    resources.getString(R.string.turnInternet),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }


    private fun imageValidation(): Boolean {
        if (!imageFlag) {
            Toast.makeText(this, "Please upload image", Toast.LENGTH_LONG).show()
            return false
        }
        return true
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

