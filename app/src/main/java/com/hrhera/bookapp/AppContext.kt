package com.hrhera.bookapp

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.util.Statics

class AppContext : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: AppContext? = null

        fun getAppContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        AppUser.create(this)
        Statics.create(this)
    }
}