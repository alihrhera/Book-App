package com.hrhera.bookapp.data.models

import java.util.*

data class AppComment(
    val id: Int,
    val user: User,
    val comment: String,
    val insertionTime: Date,


    )
