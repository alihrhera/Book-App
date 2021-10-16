package com.hrhera.bookapp.repository


import android.app.Application
import androidx.lifecycle.LiveData
import com.hrhera.bookapp.data.callbacks.CategoryDao
import com.hrhera.bookapp.data.local.DataBaseHelper
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.network.BooksNetworkCall
import com.hrhera.bookapp.util.Statics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryRepository(application: Application) {
    /*

    * MessageRepository.class will control all opp of data base
    * here will check if data base are updated will show from room data base
    * else will get data from api and insert to room app
    *
    * */

    private val categoryDao: CategoryDao
    private val allBookCategory: LiveData<List<BookCategory>>
    private val allBookCategoryWithIcon: LiveData<List<BookCategory>>

    init {
        val dataBase = DataBaseHelper.getInstance(application)
        categoryDao = dataBase.categoryDao()
        allBookCategory = categoryDao.getAllBookCategory()
        allBookCategoryWithIcon = categoryDao.getAllBookCategoryIcons()
        initCategory()
    }


    fun getAllBookCategory(): LiveData<List<BookCategory>> {
        return allBookCategory
    }

    fun getAllBookCategoryICon(): LiveData<List<BookCategory>> {
        return allBookCategoryWithIcon
    }


    private suspend fun insert(bookCategory: BookCategory) {
        categoryDao.insert(bookCategory)

    }


    fun delete(bookCategory: BookCategory) {
        categoryDao.delete(bookCategory)
    }

    private fun initCategory() {
        if (Statics.isFirstTime()) {
            GlobalScope.launch {
                categoryDao.deleteAll()
                val list = getBooksCategory()
                for (l in list) {
                    insert(l)
                }
                Statics.isNotFirstTime()
            }
        }
    }


    private val booksNetworkCall = BooksNetworkCall()

    private suspend fun getBooksCategory(): List<BookCategory> {
        return booksNetworkCall.getBookCategory()
    }

}
