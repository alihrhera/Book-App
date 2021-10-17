package com.hrhera.bookapp.util

import com.google.firebase.database.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class FBExtensions {
    fun DatabaseReference.toDeferred(): Deferred<DataSnapshot> {
        val deferred = CompletableDeferred<DataSnapshot>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                // optional, handle coroutine cancellation however you'd like here
            }
        }

        this.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                deferred.completeExceptionally(p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                deferred.complete(p0)
            }
        })
        return deferred
    }

    fun Query.toDeferred(): Deferred<DataSnapshot> {
        val deferred = CompletableDeferred<DataSnapshot>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                // optional, handle coroutine cancellation however you'd like here
            }
        }

        this.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                deferred.completeExceptionally(p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                deferred.complete(p0)
            }
        })
        return deferred
    }
}