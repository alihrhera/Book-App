package com.hrhera.bookapp.ui.fragment.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hrhera.bookapp.data.local.ripo.CategoryRepository
import com.hrhera.bookapp.data.models.BookCategory

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository    : CategoryRepository = CategoryRepository(application)
    var categoryMuLiveData    : LiveData<List<BookCategory>> = repository.getAllBookCategory()


}