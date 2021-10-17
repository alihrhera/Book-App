package com.hrhera.bookapp.repository

import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.data.network.UserNetworkCall

class UserRepository {
    private val userNetworkCall=UserNetworkCall()
    suspend fun startLogin(phone: String, password: String): Map<String, Any> {
        return userNetworkCall.userLogin(phone, password)
    }

   suspend fun startSignUp(user: User): Map<String, Any> {
        return userNetworkCall.signUp(user)
    }


    suspend fun updateUser(user:User): LoginStatus {
        return userNetworkCall.updateUser(user)
    }


}