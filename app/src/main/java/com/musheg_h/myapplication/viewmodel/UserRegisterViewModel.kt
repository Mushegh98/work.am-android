package com.musheg_h.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.musheg_h.myapplication.Repository
import com.musheg_h.myapplication.models.EmailDTO
import com.musheg_h.myapplication.models.User


class UserRegisterViewModel(application : Application) : AndroidViewModel(application) {

    fun postRegisterUser(user : User) = Repository.postRegisterUser(user)

    fun postEmail(email: EmailDTO) = Repository.postEmail(email)

}