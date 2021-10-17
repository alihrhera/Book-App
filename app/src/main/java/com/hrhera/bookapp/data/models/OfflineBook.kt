package com.hrhera.bookapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "OfflineBook") // room table name  for Message

data class OfflineBook(
    @PrimaryKey(autoGenerate = true)
    var _id: Int=0,
    var id: String,
    var name: String,
    var date: Long = Calendar.getInstance().timeInMillis
)