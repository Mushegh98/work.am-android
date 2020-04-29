package com.musheg_h.myapplication.models

import java.util.*
import java.util.logging.Level

//data class Work(var imageWork : Int , var position : String , var companyName : String , var deadLine : String , var locationCompany : String) {
//}
data class Work( var level : com.musheg_h.myapplication.utils.Level , var title : String ,  var deadLine : Date ) {
}
