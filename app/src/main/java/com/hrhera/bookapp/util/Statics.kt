package com.hrhera.bookapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.database.FirebaseDatabase
import com.hrhera.bookapp.R
import com.google.firebase.firestore.FirebaseFirestore




const val USER_NAME = "Name"
const val USER_ID = "ID"
const val USER_PHONE = "Phone"
const val USER_PHOTO = "Photo"
const val USER_INTERESTS = "Interests"
const val FIRST_TIME = "FirstTime"

object Statics {
    val fireBaseDataBase= FirebaseDatabase.getInstance()
    @SuppressLint("StaticFieldLeak")
    val fireBaseFirestore = FirebaseFirestore.getInstance()
    val icons = mutableMapOf<String, Int>()
    init {
        icons["Love"] = R.drawable.ic_love
        icons["Adventure"] = R.drawable.ic_advi
        icons["Classics"] = R.drawable.ic_clasic
        icons["Graphic"] = R.drawable.ic_graph
        icons["Mystery"] = R.drawable.ic_mistry
        icons["Historical"] = R.drawable.ic_history
        icons["Fantasy"] = R.drawable.ic_fantasy
        icons["Literary"] = R.drawable.ic_liter
        icons["Horror"] = R.drawable.ic_horr
    }
    private var shard: SharedPreferences? = null

    fun create(context: Context) {
        shard = context.getSharedPreferences("user_info", 0)
    }

    fun isFirstTime(): Boolean {
        return shard?.getBoolean(FIRST_TIME, true) ?: true
//        return true
    }


    fun isNotFirstTime() {
        shard?.edit()?.putBoolean(FIRST_TIME, false)?.apply()
    }
}