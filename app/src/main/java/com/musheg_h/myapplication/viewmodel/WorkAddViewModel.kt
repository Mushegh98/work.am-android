package com.musheg_h.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.musheg_h.myapplication.Repository
import com.musheg_h.myapplication.models.PostDTO

class WorkAddViewModel(application : Application) : AndroidViewModel(application) {

    fun postAddWork(postDTO: PostDTO, token: String) = Repository.postAddWork(token = token,postDTO = postDTO)
}