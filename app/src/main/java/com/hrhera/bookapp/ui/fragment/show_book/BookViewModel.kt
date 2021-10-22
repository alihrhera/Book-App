package com.hrhera.bookapp.ui.fragment.show_book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.repository.BookRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val oneBookMutableLiveData = MutableLiveData<OneBook>()
    private val bookRepository: BookRepository = BookRepository(application)


    val updateStatusMutableLiveData = MutableLiveData<UpdateStatus>()


    fun oneBookLiveData(): LiveData<OneBook> {
        return oneBookMutableLiveData
    }

    fun setSingleBook(book: OneBook) {
        oneBookMutableLiveData.postValue(book)
    }


    fun updateBook(book: OneBook) {
        updateStatusMutableLiveData.postValue(UpdateStatus.LOADING)
        GlobalScope.launch {
            val statues = bookRepository.updateBook(book)
            updateStatusMutableLiveData.postValue(statues)
            oneBookMutableLiveData.postValue(book)
        }
    }


    fun addToFavorite(book: OneBook) {
        updateStatusMutableLiveData.postValue(UpdateStatus.LOADING)
        GlobalScope.launch {
            val statues = bookRepository.updateBook(book)
            bookRepository.addToFavorite(book)
            updateStatusMutableLiveData.postValue(statues)
            oneBookMutableLiveData.postValue(book)
        }
    }


    fun removeFromFavorite(book: OneBook) {
        updateStatusMutableLiveData.postValue(UpdateStatus.LOADING)
        GlobalScope.launch {
            val statues = bookRepository.updateBook(book)
            bookRepository.delete(book)
            updateStatusMutableLiveData.postValue(statues)
            oneBookMutableLiveData.postValue(book)

        }
    }

}