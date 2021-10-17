package com.hrhera.bookapp.data.network

import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.data.enums.SignupStatus
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.util.Statics
import kotlinx.coroutines.CompletableDeferred

class UserNetworkCall {
    suspend fun userLogin(phone: String, password: String): Map<String, Any> {
        val myRef: DatabaseReference = Statics.fireBaseDataBase.getReference("Users")
        return myRef.orderByKey().equalTo(phone).toDeferred(password)
    }

    private suspend fun Query.toDeferred(password: String): Map<String, Any> {
        val deferred = CompletableDeferred<Map<String, Any>>()
        val map = mutableMapOf<String, Any>()
        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                map["Status"] = LoginStatus.SOME_ERROR
                deferred.complete(map)
            }
        }

        this.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                map["Status"] = LoginStatus.SOME_ERROR
                deferred.complete(map)
            }

            override fun onDataChange(s: DataSnapshot) {
                if (s.exists()) {
                    for (ss in s.children) {
                        if (ss.getValue(User::class.java)?.password == password) {
                            val user = ss.getValue(User::class.java)
                            AppUser.setUser(user!!)
                            map["Status"] = LoginStatus.SUCCESS
                            map["data"] = user
                            deferred.complete(map)
                            return
                        }
                    }
                    map["Status"] = LoginStatus.PASSWORD_ERROR
                    deferred.complete(map)
                    return
                }
                map["Status"] = LoginStatus.PHONE_ERROR
                deferred.complete(map)
            }
        })
        return deferred.await()
    }

    suspend fun signUp(user: User): Map<String, Any> {
        val myRef: DatabaseReference = Statics.fireBaseDataBase.getReference("Users")
        val deferred = CompletableDeferred<Map<String, Any>>()

        val userRef = myRef.orderByKey().equalTo(user.phone).toDeferred(user.password)
        if (userRef["Status"] == LoginStatus.SUCCESS ||
            userRef["Status"] == LoginStatus.PASSWORD_ERROR
        ) {
            deferred.complete(
                mapOf(
                    "Status" to SignupStatus.ALREADY_USER,
                    "data" to user
                )
            )
        } else if (userRef["Status"] == LoginStatus.PHONE_ERROR) {

            myRef.child(user.phone).setValue(user).addOnCompleteListener {
                if (it.isSuccessful) {
                    deferred.complete(
                        mapOf(
                            "Status" to SignupStatus.SUCCESS,
                            "data" to user
                        )
                    )
                } else if (it.isCanceled) {
                    deferred.complete(
                        mapOf(
                            "Status" to SignupStatus.SOME_ERROR,
                            "data" to user
                        )
                    )
                }
            }


        }
        return deferred.await()
    }


    private suspend fun Task<Void>.toDeferred(): LoginStatus {
        val deferred = CompletableDeferred<LoginStatus>()
        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                deferred.complete(LoginStatus.SOME_ERROR)
            }
        }

        this.addOnCompleteListener {
            if (it.isSuccessful) {
                deferred.complete(LoginStatus.SUCCESS)
            }
        }
        return deferred.await()
    }


    suspend fun updateUser(user: User): LoginStatus {
        val myRef: DatabaseReference = Statics.fireBaseDataBase.getReference("Users")
        return myRef.child(user.phone).updateChildren(user.toMap()).toDeferred()
    }


}