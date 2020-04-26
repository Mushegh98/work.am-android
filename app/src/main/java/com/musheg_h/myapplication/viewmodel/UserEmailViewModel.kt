package com.musheg_h.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.musheg_h.myapplication.Repository
import com.musheg_h.myapplication.models.User

class UserEmailViewModel(application : Application) : AndroidViewModel(application) {

    fun postRegisterUser(user : User) = Repository.postRegisterUser(user)

}