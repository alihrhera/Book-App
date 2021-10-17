package com.hrhera.bookapp.util

import android.text.TextUtils


fun String.isInvalidPone(): Boolean {
    return !(this.length == 11 && this.startsWith("01"))
}


fun String.isInvalidPassword(): Boolean {
    return this.length <= 5
}

fun String.isInvalidEmail(): Boolean {
    return TextUtils.isEmpty(this) || !(android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches())

}