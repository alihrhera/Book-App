package com.hrhera.bookapp.ui.fragment.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.repository.BookRepository
import com.hrhera.bookapp.repository.CategoryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CategoryRepository = CategoryRepository(application)
    private val bookRepository: BookRepository = BookRepository(application)


    var categoryMuLiveDataIcon: LiveData<List<BookCategory>> = repository.getAllBookCategoryICon()


    private val sliderMuLiveData = MutableLiveData<List<OneBook>>()
    private val popularMuLiveData = MutableLiveData<Map<String, Any>>()
    private val recommendedMuLiveData = MutableLiveData<Map<String, Any>>()

     fun sliderLiveData(): LiveData<List<OneBook>> {
        return sliderMuLiveData
    }

     fun recommendedLiveData(): LiveData<Map<String, Any>> {
        return recommendedMuLiveData
    }


     fun popularLiveData(): LiveData<Map<String, Any>> {
        return popularMuLiveData
    }


    fun getSliderData() {
        GlobalScope.launch {
            val list=bookRepository.getSliderBook()
            sliderMuLiveData.postValue(list)

        }
    }


    fun getPopularData() {
        GlobalScope.launch {
            val value = bookRepository.getTrendBooks()
            popularMuLiveData.postValue(value)
        }

    }

    fun getRecommendedData() {

        GlobalScope.launch {
            val value = bookRepository.getBooks()
            recommendedMuLiveData.postValue(value)
        }

    }


}