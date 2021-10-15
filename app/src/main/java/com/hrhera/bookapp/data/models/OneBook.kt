package com.hrhera.bookapp.data.models

import java.util.*

data class OneBook(
    var id: String = "",
    var name: String = "",
    var phone: String = "",
    var details: String = "",
    var photo: String = "",
    var insertionTime: Date = Date(),
    var likesTime: Int = 0,
    var commentList: MutableList<AppComment> = mutableListOf(),
    var category: BookCategory = BookCategory(),
    var user: User = User()
)
