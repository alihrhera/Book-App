package com.hrhera.bookapp.ui.fragment.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.data.models.User
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val sliderMuLiveData = MutableLiveData<List<OneBook>>()
    private val popularMuLiveData = MutableLiveData<List<OneBook>>()
    private val recommendedMuLiveData = MutableLiveData<List<OneBook>>()
    private val categoryMuLiveData = MutableLiveData<List<BookCategory>>()

    public fun sliderLiveData(): LiveData<List<OneBook>> {
        return sliderMuLiveData
    }

    public fun recommendedLiveData(): LiveData<List<OneBook>> {
        return recommendedMuLiveData
    }


    public fun popularLiveData(): LiveData<List<OneBook>> {
        return popularMuLiveData
    }


    public fun categoryLiveData(): LiveData<List<BookCategory>> {
        return categoryMuLiveData
    }


    fun getSliderData() {
        val list= mutableListOf(
            OneBook(photo = "https://img.freepik.com/free-psd/business-stationery-mock-up-arrangement-cover-books_23-2148700184.jpg?size=626&ext=jpg"),
            OneBook(photo = "https://thumbs.dreamstime.com/b/poster-cover-book-design-template-space-photo-background-use-annual-report-proposal-portfolio-brochure-flyer-leaflet-153999686.jpg"),
            OneBook(photo = "https://selfpublishingadvice.org/wp-content/uploads/2020/09/ALLi-Final-30.jpg"),
            OneBook(photo = "https://image.freepik.com/free-vector/geometric-book-cover-flyer-designs-set_1017-18063.jpg"),
        )
        Log.e("TAG", "getSliderData: ${list.size}" )
        sliderMuLiveData.postValue(list)
    }


    fun getPopularData() {

    }

    fun getRecommendedData() {

    }

    fun getCategoryData() {

    }

}