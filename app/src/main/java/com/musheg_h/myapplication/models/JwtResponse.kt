package com.musheg_h.myapplication.models

import com.musheg_h.myapplication.utils.UserType

data class JwtResponse(var token : String , var userType: UserType , var username : String) {
}