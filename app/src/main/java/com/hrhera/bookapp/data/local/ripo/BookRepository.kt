package era.apps.happinessjar.data.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.hrhera.bookapp.data.callbacks.BookDao
import com.hrhera.bookapp.data.local.DataBaseHelper
import com.hrhera.bookapp.data.models.OneBook
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookRepository(application: Application) {
    private val dataBase = DataBaseHelper.getInstance(application)
//
//    private var storyDao: BookDao = dataBase.bookDao()
//    var allLikesBook: LiveData<List<OneBook>> = storyDao.allStory



//    fun getAllStories(): LiveData<List<Story>> {
//        return allStories
//    }


//    suspend fun insert(oneBook: OneBook) {
//        storyDao.insert(oneBook)
//    }
//    suspend fun delete(oneBook: OneBook) {
//        storyDao.insert(oneBook)
//    }



}