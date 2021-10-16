package com.hrhera.bookapp.data.callbacks

import com.hrhera.bookapp.data.enums.LoginStatus

interface OnUserLogin {
    fun onLogin(loginStatus: LoginStatus)
}