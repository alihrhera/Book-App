package com.hrhera.bookapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import java.util.*

@Entity(tableName = "BookCategory") // room table name  for Message
data class BookCategory(
    @PrimaryKey(autoGenerate = true)
    @Exclude
    var _id: Int = 0,
    var id: String = "",
    var name: String = "",
    var photo: String = "",
    var iconName:String="",
    var insertionTime: Long = Calendar.getInstance().timeInMillis,
)
