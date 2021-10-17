package com.hrhera.bookapp.repository

import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.data.network.UserNetworkCall
import kotlinx.coroutines.Deferred

class UserRepository {
    private val userNetworkCall=UserNetworkCall()
    fun startLogin(phone: String, password: String): Deferred<LoginStatus> {
        return userNetworkCall.userLogin(phone, password)
    }
}