package com.hrhera.bookapp.data.models

import androidx.room.PrimaryKey
import java.util.*

data class OneBook(
    @PrimaryKey(autoGenerate = true) var _id: Int = 0,
    var id: String = "",
    var name: String = "",
    var phone: String = "",
    var details: String = "",
    var photo: String = "",
    var insertionLong: Long = Calendar.getInstance().timeInMillis,
    var likesTime: Int = 0,
    var listOfLikes: MutableList<String> = mutableListOf(),
    var commentList: MutableList<AppComment> = mutableListOf(),
    var categoryId: String ="",
    var likeIt: Int = 0,
    var openTimes: Int = 0,
    var downTimes: Int = 0,
) {
    fun toMap(): Map<String, Any> {
        val mapOfData = mutableMapOf<String, Any>()
        mapOfData["id"] = id
        mapOfData["name"] = name
        mapOfData["phone"] = phone
        mapOfData["details"] = details
        mapOfData["photo"] = photo
        mapOfData["insertionLong"] = insertionLong
        mapOfData["likesTime"]     = listOfLikes.size
        mapOfData["listOfLikes"]   = listOfLikes
        mapOfData["commentList"]   = commentList
        mapOfData["categoryId"]    = categoryId
        mapOfData["openTimes"]     = openTimes
        mapOfData["downTimes"]     = downTimes
        return mapOfData
    }
}
