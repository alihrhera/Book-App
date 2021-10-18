package com.hrhera.bookapp.ui.fragment.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrhera.bookapp.repository.CategoryRepository
import com.hrhera.bookapp.data.models.BookCategory

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CategoryRepository = CategoryRepository(application)
    var categoryMuLiveData: LiveData<List<BookCategory>> = repository.getAllBookCategory()
    private val shearedCategory = MutableLiveData<BookCategory>()

    fun shearedCategoryLiveData(): LiveData<BookCategory> {
        return shearedCategory
    }

    fun setShearedBookCategory(bookCategory: BookCategory) {
        shearedCategory.postValue(bookCategory)
    }


}