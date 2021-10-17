package com.hrhera.bookapp.data.network

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.util.Statics.fireBaseFirestore
import kotlinx.coroutines.CompletableDeferred


class BooksNetworkCall {

    suspend fun getAllBooks(): Map<String, Any> {

        return fireBaseFirestore.collection("Books")
            .get().toDeferred()
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
                if (it.isSuccessful){
                    deferred.complete(UpdateStatus.SUCCESS)

                }else {
                    deferred.complete(UpdateStatus.SOME_ERROR)
                    Log.e("Some Error ", "updateBook: ${it.exception?.message}" )

                }

            }

        return deferred.await()
    }


    /*
    private suspend fun addBook() {
        var time = Calendar.getInstance().timeInMillis
        var oneBook = OneBook(
            phone = AppUser.getPhone(),
            photo = "https://images-na.ssl-images-amazon.com/images/I/61ZKNw0xixL.jpg",
            name = "Test Me 1 ",
            insertionLong = time,
            id = "$time",
            category = DataManger.listOfBookCategory[0]

        )
        Statics.fireBaseFirestore.collection("Books")
            .document(oneBook.id).set(oneBook)

        delay(2)
        time = Calendar.getInstance().timeInMillis
        oneBook = OneBook(
            phone = AppUser.getPhone(),
            photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRL5WdPj7Xp5z1X2To-EQeTdIRmgG7SbiX0XKX81Igr2ulTYnYowUlf3noI9tZFwCYcZU4&usqp=CAU",
            name = "Test Me 2 ",
            insertionLong = time,
            id = "$time",
            category = DataManger.listOfBookCategory[0]

        )
        Statics.fireBaseFirestore.collection("Books")
            .document(oneBook.id).set(oneBook)
        delay(2)
        time = Calendar.getInstance().timeInMillis
        oneBook = OneBook(
            phone = AppUser.getPhone(),
            photo = "https://i.pinimg.com/474x/8d/89/cc/8d89ccc3ab38d7d876a27e15705853c9--andrew-ross-sorkin-great-depression.jpg",
            name = "Test Me 3 ",
            insertionLong = time,
            id = "$time",
            category = DataManger.listOfBookCategory[0]

        )
        Statics.fireBaseFirestore.collection("Books")
            .document(oneBook.id).set(oneBook)

        delay(2)
        time = Calendar.getInstance().timeInMillis
        oneBook = OneBook(
            phone = AppUser.getPhone(),
            name = "Test Me 4",
            photo = "https://api.time.com/wp-content/uploads/2015/06/jaws.jpg?quality=85",
            insertionLong = time,
            id = "$time",
            category = DataManger.listOfBookCategory[0]

        )
        Statics.fireBaseFirestore.collection("Books")
            .document(oneBook.id).set(oneBook)




        delay(2)
        time = Calendar.getInstance().timeInMillis
        oneBook = OneBook(
            phone = AppUser.getPhone(),
            name = "Test Me 5",
            photo = "https://cdn.pastemagazine.com/www/system/images/photo_albums/best-book-covers-fall-2019/large/bbcdune.jpg?1384968217",
            insertionLong = time,
            id = "$time",
            category = DataManger.listOfBookCategory[1]

        )
        Statics.fireBaseFirestore.collection("Books")
            .document(oneBook.id).set(oneBook)

        delay(2)
        time = Calendar.getInstance().timeInMillis
        oneBook = OneBook(
            phone = AppUser.getPhone(),
            name = "Test Me 6",
            photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRak-7tVzweDKazAe9v_BzMr18uehtvqcTqpg&usqp=CAU",
            insertionLong = time,
            id = "$time",
            category = DataManger.listOfBookCategory[1]

        )
        Statics.fireBaseFirestore.collection("Books")
            .document(oneBook.id).set(oneBook)


    }
    */
}