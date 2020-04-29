package com.musheg_h.myapplication

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.musheg_h.myapplication.models.*
import com.musheg_h.myapplication.network.ApiClient
import com.musheg_h.myapplication.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Repository {

    private var apiInterface: ApiInterface = ApiClient.getClient().create(
        ApiInterface::class.java
    )


    fun postLoginUser(user: UserLogin , context: Context): LiveData<JwtResponse> {
        val liveData = MutableLiveData<JwtResponse>()

        val call: Call<JwtResponse> = apiInterface.postLoginUser(user)
        call.enqueue((object : Callback<JwtResponse> {
            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                Toast.makeText(context,"Incorrect username or password",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                val code = response.code()
                if(code==401)
                {
                    Toast.makeText(context,"Incorrect username or password",Toast.LENGTH_SHORT).show()
                }
                else {
                    val list = response.body()!!
                    liveData.value = list
                    //  insert(list)
                }
            }
        }))
        return liveData
    }


    fun postRegisterUser(user: User): LiveData<User> {
        val liveData = MutableLiveData<User>()
        val call: Call<User> = apiInterface.postRegisterUser(user)

        call.enqueue((object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                var code = response.code()
                val list: User = response.body()!!
                liveData.value = list
                //  insert(list)
            }
        }))
        return liveData
    }


    fun postHelloUser(token: String): LiveData<JwtResponse> {
        val liveData = MutableLiveData<JwtResponse>()
        val map = HashMap<String, String>()
        map.put("Authorization", "Bearer" + " " + token)
        val call: Call<JwtResponse> = apiInterface.postHelloUser(map)

        call.enqueue((object : Callback<JwtResponse> {
            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                var code = response.code()
                val list: JwtResponse = response.body()!!
                liveData.value = list
                //  insert(list)
            }
        }))
        return liveData
    }


    fun postEmail(email: EmailDTO): LiveData<EmailDTO> {
        val liveData = MutableLiveData<EmailDTO>()
        val call: Call<EmailDTO> = apiInterface.postEmailUser(email)
        call.enqueue((object : Callback<EmailDTO> {
            override fun onFailure(call: Call<EmailDTO>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<EmailDTO>, response: Response<EmailDTO>) {
                var code = response.code()
                val email: EmailDTO = response.body()!!
                liveData.value = email
                //  insert(list)
            }
        }))
        return liveData
    }


    fun postAddWork(postDTO: PostDTO,token: String) : LiveData<DAOPost>
    {
        val map : HashMap<String,String> = HashMap()
        map["Authorization"] = token
        val liveData = MutableLiveData<DAOPost>()
        val call: Call<DAOPost> = apiInterface.postAddWork(map,postDTO)
        call.enqueue((object : Callback<DAOPost> {
            override fun onFailure(call: Call<DAOPost>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<DAOPost>, response: Response<DAOPost>) {
                var code = response.code()
                val post: DAOPost = response.body()!!
                liveData.value = post
                //  insert(list)
            }
        }))
        return liveData
    }

    fun postListCompany(token : String,pageDTO: PageDTO) : LiveData<List<DAOPost>>
    {
        val map : HashMap<String,String> = HashMap()
        map["Authorization"] = token
        val liveData = MutableLiveData<List<DAOPost>>()
        val call: Call<List<DAOPost>> = apiInterface.postListCompany(map,pageDTO)
        call.enqueue((object : Callback<List<DAOPost>> {
            override fun onFailure(call: Call<List<DAOPost>>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<List<DAOPost>>, response: Response<List<DAOPost>>) {
                var code = response.code()
                val post = response.body()!!
                liveData.value = post
                //  insert(list)
            }
        }))
        return liveData
    }


    fun postListPerson(token : String,pageDTO: PageDTO) : LiveData<List<DAOPost>>
    {
        val map : HashMap<String,String> = HashMap()
        map["Authorization"] = token
        val liveData = MutableLiveData<List<DAOPost>>()
        val call: Call<List<DAOPost>> = apiInterface.postListPerson(map,pageDTO)
        call.enqueue((object : Callback<List<DAOPost>> {
            override fun onFailure(call: Call<List<DAOPost>>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!)
            }

            override fun onResponse(call: Call<List<DAOPost>>, response: Response<List<DAOPost>>) {
                var code = response.code()
                val post = response.body()!!
                liveData.value = post
                //  insert(list)
            }
        }))
        return liveData
    }


}