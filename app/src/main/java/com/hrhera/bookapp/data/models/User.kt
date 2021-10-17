package com.hrhera.bookapp.data.models

import com.google.firebase.firestore.Exclude

data class User(
    var id      : String = "",
    var name    : String = "",
    var phone   : String = "",
    var photo   : String = "",
    var email   : String = "userEmail@exa.ple",
    @Exclude
    var password:String="",
    var setOfInterests: List<BookCategory> = listOf()


){

    fun toMap():Map<String,Any>{
        val map= mutableMapOf<String,Any>()
        map["name"]=name
        map["phone"]=phone
        map["photo"]=photo
        map["email"]=email
        map["password"]=password
        map["setOfInterests"]=setOfInterests
        return map

    }
}