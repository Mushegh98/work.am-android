package com.musheg_h.myapplication.models

import android.os.Parcelable
import com.musheg_h.myapplication.utils.UserType

data class User(var username : String? , var password : String  , var email : String? , var userType : UserType? , var code : Int)  {
}