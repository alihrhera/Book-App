package com.hrhera.bookapp.data.network

import com.google.firebase.database.*
import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.util.Statics
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class UserNetworkCall {
    fun userLogin(phone: String, password: String): Deferred<LoginStatus> {
        val myRef: DatabaseReference = Statics.fireBaseDataBase.getReference("Users")
        return myRef.orderByKey().equalTo(phone).toDeferred(password)
    }

    private fun Query.toDeferred(password: String): Deferred<LoginStatus> {
        val deferred = CompletableDeferred<LoginStatus>()
        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                deferred.complete(LoginStatus.SOME_ERROR)
            }
        }

        this.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                deferred.complete(LoginStatus.SOME_ERROR)
            }

            override fun onDataChange(s: DataSnapshot) {
                if (s.exists()) {
                    for (ss in s.children) {
                        if (ss.getValue(User::class.java)?.password == password) {
                            val user = ss.getValue(User::class.java)
                            AppUser.setUser(user!!)
                            deferred.complete(LoginStatus.SUCCESS)
                            return
                        }
                    }
                    deferred.complete(LoginStatus.PASSWORD_ERROR)
                }
                deferred.complete(LoginStatus.PHONE_ERROR)
            }
        })
        return deferred
    }

}