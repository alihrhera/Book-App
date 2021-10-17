package com.hrhera.bookapp.ui.fragment.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.enums.SignupStatus
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.repository.UserRepository
import com.hrhera.bookapp.util.isInvalidEmail
import com.hrhera.bookapp.util.isInvalidPassword
import com.hrhera.bookapp.util.isInvalidPone
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private var signUpMutableLiveData = MutableLiveData<Map<String, Any>>()
    private val userRepository = UserRepository()


    fun signUp(user: User) {
        val map = mutableMapOf<String, Any>()
        if (user.name.length < 2) {
            map["Status"] = SignupStatus.NAME_ERROR
            signUpMutableLiveData.postValue(map)
            return
        }
        if (user.email.isInvalidEmail()) {
            map["Status"] = SignupStatus.EMAIL_ERROR
            signUpMutableLiveData.postValue(map)
            return
        }



        if (user.phone.isInvalidPone()) {
            map["Status"] = SignupStatus.PHONE_INVALID
            signUpMutableLiveData.postValue(map)
            return
        }


        if (user.password.isInvalidPassword()) {
            map["Status"] = SignupStatus.PASSWORD_INVALID
            signUpMutableLiveData.postValue(map)
            return
        }

        map["Status"] = SignupStatus.LOADING
        signUpMutableLiveData.postValue(map)
        GlobalScope.launch {
            val value = userRepository.startSignUp(user)
            signUpMutableLiveData.postValue(value)
        }
    }

    fun signUpLiveData(): LiveData<Map<String, Any>> {
        return signUpMutableLiveData
    }

}