package com.hrhera.bookapp.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hrhera.bookapp.data.callbacks.BookDao
import com.hrhera.bookapp.data.callbacks.CategoryDao
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OfflineBook
import com.hrhera.bookapp.data.models.OneBook


@Database(entities = [BookCategory::class,OfflineBook::class], version = 1)
abstract class DataBaseHelper : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun bookDao(): BookDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseHelper::class.java,
                    "BookApp"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
