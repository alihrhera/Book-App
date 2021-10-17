package com.hrhera.bookapp.data.models

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.hrhera.bookapp.util.*

object AppUser {

    private lateinit var shard: SharedPreferences

    fun create(context: Context) {
        shard = context.getSharedPreferences("user_info", 0)
    }

    private lateinit var user: User

    fun setUser(user: User) {
        this.user = user
        shard.edit().putString(USER_ID, user.id).apply()
        shard.edit().putString(USER_NAME, user.name).apply()
        shard.edit().putString(USER_PHONE, user.phone).apply()
        shard.edit().putString(USER_PHOTO, user.photo).apply()
        Log.e("TAG", "getPhone: ${user.phone}")

        val inter = mutableSetOf<String>()
        for (i in user.setOfInterests) {
            inter.add(i.id)
        }
        shard.edit().putStringSet(USER_INTERESTS, inter).apply()
    }


    fun getName(): String {
        return shard.getString(USER_NAME, "User Name") ?: "User Name"
    }


    fun getPhone(): String {
        val phone = shard.getString(USER_PHONE, "User Phone") ?: "User Phone"
        Log.e("TAG", "getPhone: $phone")
        return phone
    }


    fun getPhoto(): String {
        return shard.getString(USER_PHOTO, "User Photo") ?: "User Photo"
    }

    fun getId(): String {
        return shard.getString(USER_ID, "User ID") ?: "User ID"
    }

    private
    fun getUser(): User {
        val setOfInterests = mutableSetOf<BookCategory>()
        val userSet = shard.getStringSet(USER_INTERESTS, setOf()) ?: setOf()
        if (userSet.isNotEmpty()) {
            for (i in DataManger.listOfBookCategory) {
                if (userSet.contains(i.id)) {
                    setOfInterests.add(i)
                }
            }
        }

        return User(getId(), getName(), getPhone(), getPhoto())
    }


}