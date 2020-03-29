package com.musheg_h.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log

const val BASE_URL = "http://62.89.30.125:8000"

enum class UserType
{
    PERSON , COMPANY
}

fun isNetworkAvailable(context : Context) : Boolean {

    val connectivityManager : ConnectivityManager  =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
       val capabilities : NetworkCapabilities? = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                return true
            }
        }
    }

    else {
        try {
            val activeNetworkInfo : NetworkInfo? = connectivityManager.getActiveNetworkInfo()
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                Log.i("update_status", "Network is available : true")
                return true
            }
        } catch (e : Exception ) {
            Log.i("update_status", "" + e.message)
        }
    }
    Log.i("update_status","Network is available : FALSE ");
    return false
}


