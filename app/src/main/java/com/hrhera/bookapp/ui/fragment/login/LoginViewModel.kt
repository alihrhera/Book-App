package com.hrhera.bookapp.ui.fragment.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.repository.UserRepository
import com.hrhera.bookapp.util.isInvalidPassword
import com.hrhera.bookapp.util.isInvalidPone
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var loginMutableLiveData = MutableLiveData<Map<String,Any>>()
    private val userRepository=UserRepository()


    fun login(phone: String, password: String) {
        val map= mutableMapOf<String,Any>()

        if (phone.isInvalidPone()) {
            map["Status"]=LoginStatus.PHONE_INVALID
            loginMutableLiveData.postValue(map)
            return
        }
        if (password.isInvalidPassword()) {
            map["Status"]=LoginStatus.PASSWORD_INVALID
            loginMutableLiveData.postValue(map)
            return
        }

        map["Status"]=LoginStatus.LOADING
        loginMutableLiveData.postValue(map)
        GlobalScope.launch {
            val value =userRepository.startLogin(phone, password)
            loginMutableLiveData.postValue(value)
        }
    }

    fun loginLiveData(): LiveData<Map<String,Any>> {
        return loginMutableLiveData
    }



}