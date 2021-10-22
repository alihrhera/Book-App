package com.hrhera.bookapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.local.DataBaseHelper
import com.hrhera.bookapp.data.models.OfflineBook
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.data.network.BooksNetworkCall

class BookRepository(application: Application) {
    private val booksNetworkCall = BooksNetworkCall()
    private val dataBase = DataBaseHelper.getInstance(application).bookDao()
    var allLikesBook: LiveData<List<OfflineBook>> = dataBase.allBooks

    suspend fun insert(oneBook: OneBook) {
        val book = OfflineBook(id = oneBook.id, name = oneBook.name)
        dataBase.insert(book)
    }

    fun delete(oneBook: OneBook) {
        dataBase.delete(oneBook.id)
    }


    suspend fun getBooks(): Map<String, Any> {
        return booksNetworkCall.getAllBooks()
    }

    suspend fun getTrendBooks(): Map<String, Any> {
        return booksNetworkCall.getTrendBooks()
    }

    suspend fun getSliderBook(): List<OneBook> {
        return booksNetworkCall.getSliderBook()
    }


    suspend fun updateBook(book: OneBook): UpdateStatus {
        return booksNetworkCall.updateBook(book)
    }

    suspend fun addToFavorite(book: OneBook) {
        insert(book)
    }



}