package com.musheg_h.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.musheg_h.myapplication.models.JwtResponse
import com.musheg_h.myapplication.models.User
import com.musheg_h.myapplication.models.UserLogin
import com.musheg_h.myapplication.network.ApiClient
import com.musheg_h.myapplication.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Repository {

    private var apiInterface: ApiInterface = ApiClient.getClient().create(
        ApiInterface::class.java
    )


    fun postLoginUser(user: UserLogin) : LiveData<JwtResponse>
    {
        val liveData = MutableLiveData<JwtResponse>()
        val call: Call<JwtResponse> = apiInterface.postLoginUser(user)
        call.enqueue((object : Callback<JwtResponse>
        {
            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                Log.d("TAG",  t.localizedMessage!!)
            }


            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                var code =  response.code()
                val list: JwtResponse = response.body()!!
                liveData.value = list
                //  insert(list)
            }

        }))
        return liveData
    }


    fun postRegisterUser(user : User)  : LiveData<User>
    {
        val liveData = MutableLiveData<User>()

        val call: Call<User> =  apiInterface.postRegisterUser(user)

        call.enqueue((object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG",  t.localizedMessage!!)
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
              var code =  response.code()
                val list: User = response.body()!!
                   liveData.value = list
                //  insert(list)
            }
        }))
        return liveData
    }
}