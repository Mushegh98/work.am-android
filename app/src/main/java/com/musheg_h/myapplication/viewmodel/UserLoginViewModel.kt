package com.musheg_h.myapplication.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.musheg_h.myapplication.Repository
import com.musheg_h.myapplication.models.UserLogin

class UserLoginViewModel(application : Application) : AndroidViewModel(application) {

    fun postLoginUser(user: UserLogin,context: Context) = Repository.postLoginUser(user,context)
}