package com.musheg_h.myapplication.network

import com.musheg_h.myapplication.models.JwtResponse
import com.musheg_h.myapplication.models.User
import com.musheg_h.myapplication.models.UserLogin
import com.musheg_h.myapplication.utils.UserType

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


    @POST("/register")
    fun postRegisterUser(@Body user : User)  :  Call<User>

    @POST("/authenticate")
    fun postLoginUser(@Body user : UserLogin) : Call<JwtResponse>

    @POST("/hello")
    fun postHelloUser(@HeaderMap headers :Map<String, String>) : Call<JwtResponse>

}