package com.musheg_h.myapplication.models

import com.musheg_h.myapplication.utils.UserType

data class User(var username : String , var password : String  , var email : String , var userType : UserType) {
}