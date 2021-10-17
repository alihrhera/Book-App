package com.hrhera.bookapp.ui.fragment.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val userMutableLiveData = MutableLiveData<User>()
    private val userRepository = UserRepository()

    fun userLiveData(): LiveData<User> {
        return userMutableLiveData
    }


    fun setUser(user: User) {
        userMutableLiveData.postValue(user)
    }


    fun updateUser(user: User) {
        GlobalScope.launch {
            val value = userRepository.updateUser(user)
            if (value == LoginStatus.SUCCESS) {
                userMutableLiveData.postValue(user)
            }
        }
    }


}