package com.hrhera.bookapp.data.models

import com.google.firebase.firestore.Exclude

data class User(
    var id      : String = "",
    var name    : String = "",
    var phone   : String = "",
    var photo   : String = "",
    @Exclude
    var password:String="",
    var setOfInterests: List<BookCategory> = listOf()


)