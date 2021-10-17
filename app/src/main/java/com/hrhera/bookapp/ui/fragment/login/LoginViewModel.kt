package com.hrhera.bookapp.ui.fragment.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var loginMutableLiveData = MutableLiveData<LoginStatus>()
    private val userRepository=UserRepository()


    fun login(phone: String, password: String) {
        if (isInvalidPone(phone)) {
            loginMutableLiveData.postValue(LoginStatus.PHONE_INVALID)
            return
        }
        if (isInvalidPassword(password)) {
            loginMutableLiveData.postValue(LoginStatus.PASSWORD_INVALID)
            return
        }
        loginMutableLiveData.postValue(LoginStatus.LOADING)
        GlobalScope.launch {
            val value =userRepository.startLogin(phone, password).await()
            loginMutableLiveData.postValue(value)
        }
    }

    fun loginLiveData(): LiveData<LoginStatus> {
        return loginMutableLiveData
    }


    fun isInvalidPone(phone: String): Boolean {
        return !(phone.length == 11 && phone.startsWith("01"))
    }


    fun isInvalidPassword(password: String): Boolean {
        return password.length <= 5
    }

}