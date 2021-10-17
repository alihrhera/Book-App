package com.hrhera.bookapp.data.models

import java.util.*

data class AppComment(
    val id: Int=0,
    val _id: String="",
    val user: User = User(),
    val comment: String = "",
    val insertionTime: Date = Date(),


    )
