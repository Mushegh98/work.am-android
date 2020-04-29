package com.musheg_h.myapplication.network

import com.musheg_h.myapplication.models.*

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


    @POST("/register")
    fun postRegisterUser(@Body user : User)  :  Call<User>

    @POST("/authenticate")
    fun postLoginUser(@Body user : UserLogin) : Call<JwtResponse>

    @POST("/hello")
    fun postHelloUser(@HeaderMap headers :Map<String, String>) : Call<JwtResponse>

    @POST("/verification_code")
    fun postEmailUser(@Body email : EmailDTO) : Call<EmailDTO>

    @POST("/addPost")
    fun postAddWork(@HeaderMap headers :Map<String, String> ,@Body postDTO: PostDTO) : Call<DAOPost>

    @POST("/postListCompany")
    fun postListCompany(@HeaderMap headers: Map<String, String>, @Body pageDTO : PageDTO) : Call<List<DAOPost>>

    @POST("/postListPerson")
    fun postListPerson(@HeaderMap headers: Map<String, String>, @Body pageDTO : PageDTO) : Call<List<DAOPost>>

}