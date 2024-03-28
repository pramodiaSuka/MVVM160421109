package com.maverick.studentapp.model

import com.google.gson.annotations.SerializedName

data class Motorcycle(
    var id:Int?,
    var name:String?,
    var manufacturer:String?,
    var year:Int?,
    var engine: Engine?,
    var colors: List<String>?,
    var images:String?
)
