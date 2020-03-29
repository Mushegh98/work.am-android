package com.musheg_h.myapplication.network

import com.musheg_h.myapplication.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

     companion object {
         private var retrofit : Retrofit? = null
         fun getClient() : Retrofit
         {
             if(retrofit ==null)
             {
                 retrofit = Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                    // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                     .build()
             }
             return retrofit!!
         }
    }
}