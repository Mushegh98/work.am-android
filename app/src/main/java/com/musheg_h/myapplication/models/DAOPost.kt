package com.musheg_h.myapplication.models

import com.musheg_h.myapplication.utils.Level
import java.util.*

data class DAOPost( var id: Int, var title: String, var content : String, var level : Level, var date : Date, var companyId : Int, var removed: Boolean , var companyName : String){
}