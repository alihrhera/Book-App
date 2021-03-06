package com.hrhera.bookapp.repository


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.hrhera.bookapp.data.callbacks.CategoryDao
import com.hrhera.bookapp.data.enums.AppStatus
import com.hrhera.bookapp.data.local.DataBaseHelper
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.data.network.BooksNetworkCall
import com.hrhera.bookapp.util.Statics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class CategoryRepository(application: Application) {
    /*

    * CategoryRepository.class will control all opp of data base
    * here will check if data base are updated will show from room data base
    * else will get data from api and insert to room app
    *
    * */

    private val categoryDao: CategoryDao
    private val allBookCategory: LiveData<List<BookCategory>>

    init {
        val dataBase = DataBaseHelper.getInstance(application)
        categoryDao = dataBase.categoryDao()
        allBookCategory = categoryDao.getAllBookCategory()
        initCategory()
    }


    fun getAllBookCategory(): LiveData<List<BookCategory>> {
        return allBookCategory
    }

    private suspend fun insert(bookCategory: BookCategory) {
        categoryDao.insert(bookCategory)

    }


    fun delete(bookCategory: BookCategory) {
        categoryDao.delete(bookCategory)
    }

    private fun initCategory() {
/*
        if (Statics.isFirstTime()) {
            GlobalScope.launch {
                categoryDao.deleteAll()
                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Love Story",
                        photo = "https://learnhowtowriteanovel.com/wp-content/uploads/2021/09/love-story-3060241_1920.jpg",
                        iconName = "Love",

                        )
                )

                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Action and Adventure",
                        photo = "https://lh3.googleusercontent.com/proxy/wWrmXxkuc-XqHSR-V00lUoOZG5PtA9g4H8yx-8jtnbMOVhW2yYrhF8377sz2i9r0oZVHy_9IwwVKf4plBe3-X12hctvsCiz8r9_3eMVmTclMSb0kXGUwhADyETn_1Q08gyubXrK7f8DLc6t92CJqQw",
                        iconName = "Adventure",

                        )
                )
                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Classics",
                        photo = "https://assets.brightspot.abebooks.a2z.com/dims4/default/0e4648d/2147483647/strip/true/crop/571x300+0+0/resize/1200x630!/quality/90/?url=http%3A%2F%2Fabebooks-brightspot.s3.amazonaws.com%2F37%2F79%2F21cde12b4e7e9fa16f1f882c6167%2Fclassic-books-lede.jpg",
                        iconName = "Classics",
                    )
                )
                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Comic Book or Graphic Novel",
                        photo = "https://thebuzzmagazines.com/sites/default/files/article-photos/2018/03/graphic%20novels%20mary%20resized.jpg",
                        iconName = "Graphic",
                    )
                )
                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Detective and Mystery",
                        photo = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/best-murder-mystery-books-1623267447.jpg?crop=0.469xw:1.00xh;0.302xw,0&resize=640:*",
                        iconName = "Mystery",
                    )
                )

                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Fantasy",
                        photo = "https://fantasybookdesign.com/wp-content/uploads/2020/12/org-file888.jpg",
                        iconName = "Fantasy",
                    )
                )

                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Historical Fiction",
                        photo = "https://everythingnyze.files.wordpress.com/2014/07/picmonkey-collage1.jpg",
                        iconName = "Historical",
                    )
                )

                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Horror",
                        photo = "https://bloody-disgusting.com/wp-content/uploads/2019/05/Pet-Sematary-Ellie-e1557774082128.jpg",
                        iconName = "Horror",
                    )
                )

                insert(
                    BookCategory(
                        id = "${Calendar.getInstance().timeInMillis}",
                        name = "Literary Fiction",
                        photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVuyW8cXyZgUEPm3Y-IcVrWTBN0KATKzwqflxqgGaFEEJTDI50TkWMQSSEM-28WHex6n4&usqp=CAU",
                        iconName = "Literary"
                    )
                )
                Statics.isNotFirstTime()
            }


        }
*/

             if (Statics.isFirstTime()) {
                 GlobalScope.launch {
                     categoryDao.deleteAll()
                     val list = getBookCategories()
                     for (l in list) {
                         insert(l)
                     }
                     Statics.isNotFirstTime()
                 }
             }

    }

    private val booksNetworkCall = BooksNetworkCall()

    private suspend fun getBookCategories(): List<BookCategory> {
        return booksNetworkCall.getBookCategory()
    }


    suspend fun getCategoryBooks(id: String): Map<String, Any> {
        Log.e("TAG", "getCategoryBooks: $id")

        return booksNetworkCall.getBooksByCategoryId(id)
    }


}
