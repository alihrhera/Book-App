package com.hrhera.bookapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "OneBook") // room table name  for Message
data class OneBook(
    @PrimaryKey(autoGenerate = true) var _id: Int = 0,
    var id: String = "",
    var name: String = "",
    var phone: String = "",
    var details: String = "",
    var photo: String = "",
//    var insertionTime: Date = Date(),
    var insertionLong: Long = Calendar.getInstance().timeInMillis,
    var likesTime: Int = 0,
    var commentList: MutableList<AppComment> = mutableListOf(),
    var category: BookCategory = BookCategory(),
    var user: User = User(),
    var likeIt: Int = 0
)
