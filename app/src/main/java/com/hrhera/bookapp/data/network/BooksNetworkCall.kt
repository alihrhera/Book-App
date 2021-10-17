package com.hrhera.bookapp.data.network

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.util.DataManger
import com.hrhera.bookapp.util.Statics.fireBaseFirestore
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import java.util.*


class BooksNetworkCall {

    suspend fun getAllBooks(): Map<String, Any> {
        delay(1500)
        return fireBaseFirestore.collection("Books")
            .get().toDeferred()
    }

    suspend fun getTrendBooks(): Map<String, Any> {
        delay(1500)
        return fireBaseFirestore.collection("Books")
            .whereGreaterThan("likesTime", 7)
            .orderBy("likesTime",Query.Direction.DESCENDING)
            .get().toDeferred()
    }

    suspend fun getSliderBook(): List<OneBook> {

        delay(2000)
        return mutableListOf(
            OneBook(photo = "https://img.freepik.com/free-psd/business-stationery-mock-up-arrangement-cover-books_23-2148700184.jpg?size=626&ext=jpg"),
            OneBook(photo = "https://thumbs.dreamstime.com/b/poster-cover-book-design-template-space-photo-background-use-annual-report-proposal-portfolio-brochure-flyer-leaflet-153999686.jpg"),
            OneBook(photo = "https://selfpublishingadvice.org/wp-content/uploads/2020/09/ALLi-Final-30.jpg"),
            OneBook(photo = "https://image.freepik.com/free-vector/geometric-book-cover-flyer-designs-set_1017-18063.jpg"),
        )
    }



    private suspend fun Task<QuerySnapshot>.toDeferred(): Map<String, Any> {
        val deferred = CompletableDeferred<Map<String, Any>>()
        val map = mutableMapOf<String, Any>()
        this.addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                map["error"] = false
                val list = mutableListOf<OneBook>()
                for (document in it.result!!) {
                    list.add(document.toObject(OneBook::class.java))
                }
                map["data"] = list
                deferred.complete(map)
            } else {
                map["error"] = true
                map["error_message"] = "No Data "
                map["data"] = arrayListOf<OneBook>()
                deferred.complete(map)
            }
        }
        return deferred.await()
    }


    private suspend fun Task<QuerySnapshot>.toDeferredBookCategory(): List<BookCategory> {
        val deferred = CompletableDeferred<List<BookCategory>>()

        this.addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                val list = mutableListOf<BookCategory>()
                for (document in it.result!!) {
                    list.add(document.toObject(BookCategory::class.java))
                }
                deferred.complete(list)
            } else {
                deferred.complete(arrayListOf())
            }
        }
        return deferred.await()
    }


    suspend fun getBookCategory(): List<BookCategory> {
        return fireBaseFirestore.collection("BookCategory")
            .get().toDeferredBookCategory()
    }


    suspend fun updateBook(oneBook: OneBook): UpdateStatus {
        val deferred = CompletableDeferred<UpdateStatus>()

        fireBaseFirestore.collection("Books").document(oneBook.id)
            .update(oneBook.toMap()).addOnCompleteListener {
                if (it.isSuccessful) {
                    deferred.complete(UpdateStatus.SUCCESS)

                } else {
                    deferred.complete(UpdateStatus.SOME_ERROR)
                    Log.e("Some Error ", "updateBook: ${it.exception?.message}")

                }

            }

        return deferred.await()
    }

    private suspend fun addBook() {
        var time = Calendar.getInstance().timeInMillis
        val list = mutableListOf(
            "https://cdn.flipsnack.com/landing/files/free-online-book-cover-maker",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSBloFQBVbSNh9Doj5oEpYxl_9UIyy7kHKYpyp-e4qs103DxGG7QC9eP1AKOPoAo8wCcU&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoI0BwXWQrZwSyvEx_AYITnrXsmqUMTbCjvbF-11CXCMsLMpcxkQhg5gxqUIr7ppg8lVU&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRYiCx4OSXwxurzmqSSjpHFPz0P7D3hUyvWb-Akp46FwfGihZKHxOnla-0Jl8XRz208GU&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY2TqZA7lFnu0v6FbJnbajyV3GzNpSCXXqbw7PoI2HVD7cHYlBTIfYnDfLi7gvUVlHbS0&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStlEEgbbSMVdEc_tu7VpWAgwGiF4StIDnLd4_H2Ed_GAHOn9zbZgCv3IaloeJP0OojR_8&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLRhU8yy3aTwFrF7TS2T3r39dFhkKUrQ4xxQ-pqvrVd-j38a1Y-OjuIPmhLaDu9m2_cz4&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeoOqLzvvz6dSDAo_zMCXASHP7BgRVpR8vRMj7ZGvCiw0seafBhzfSWU4aFJ7KJnISzw4&usqp=CAU",
            "https://ecolechezdonald.com/wp-content/uploads/2020/05/book-img2.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkdK8w335-t3zf-OwQNY9esM0NOtrE4UNyA&usqp=CAU",
            "https://i.ytimg.com/vi/obdMfXTlyIM/maxresdefault.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/002/110/581/small_2x/abstract-geometric-cover-design-free-vector.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5dGhop2FrtXxEEV2ILK4qVs5lungPY8V_4g&usqp=CAU",
            "https://images-na.ssl-images-amazon.com/images/I/41UHen-IwaL._SX327_BO1,204,203,200_.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStCPJA0qw9z1h_7V352SWBVxwhKMuz-lK2-A&usqp=CAU",
            "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/action-thriller-book-cover-design-template-3675ae3e3ac7ee095fc793ab61b812cc.jpg?ts=1588152105",
        )
        val catList = DataManger.listOfBookCategory
        catList.shuffle()
        list.shuffle()
        for ((i, c) in catList.withIndex()) {
            time += 1
            val oneBook = OneBook(
                phone = AppUser.getPhone(),
                photo = list[i],
                name = " Me Test  ${i + 25}",
                insertionLong = time,
                id = "$time",
                category = c
            )
            fireBaseFirestore.collection("Books")
                .document(oneBook.id).set(oneBook)
            delay(1)
        }

    }

}