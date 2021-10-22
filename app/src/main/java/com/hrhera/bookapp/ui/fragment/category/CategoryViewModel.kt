package com.hrhera.bookapp.ui.fragment.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.data.enums.AppStatus
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.repository.CategoryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CategoryRepository = CategoryRepository(application)
    var categoryMuLiveData: LiveData<List<BookCategory>> = repository.getAllBookCategory()
    val singleCategoryMutableLiveData= MutableLiveData<BookCategory>()

    private var booksOfCategoryMuLiveData = MutableLiveData<Map<String, Any>>()

    fun setSingleBookCategory(bookCategory: BookCategory){
        singleCategoryMutableLiveData.postValue(bookCategory)
        setBookListOfCategory(bookCategory.id)
    }

    private fun setBookListOfCategory(id:String){
        booksOfCategoryMuLiveData.postValue(mapOf("status" to AppStatus.LOADING))
        GlobalScope.launch {
            val value=repository.getCategoryBooks(id)
            booksOfCategoryMuLiveData.postValue(value)
        }

    }


    fun booksOfCategoryLiveData():LiveData<Map<String, Any>>{
        return booksOfCategoryMuLiveData
    }




}