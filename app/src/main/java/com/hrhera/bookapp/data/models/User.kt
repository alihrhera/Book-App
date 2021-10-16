package com.hrhera.bookapp.data.models

data class User(
    var id: String = "",
    var name: String = "",
    var phone: String = "",
    var photo: String = "",
    var setOfInterests: Set<BookCategory> = setOf()
)