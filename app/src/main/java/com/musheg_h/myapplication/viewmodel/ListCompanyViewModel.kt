package com.musheg_h.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.musheg_h.myapplication.Repository
import com.musheg_h.myapplication.models.PageDTO

class ListCompanyViewModel(application : Application) : AndroidViewModel(application) {

    fun postListCompany(token : String,pageDTO: PageDTO) = Repository.postListCompany(token, pageDTO)

    fun postListPerson(token : String,pageDTO: PageDTO) = Repository.postListPerson(token, pageDTO)

}