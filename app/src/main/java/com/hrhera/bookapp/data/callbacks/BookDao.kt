package com.hrhera.bookapp.data.callbacks


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hrhera.bookapp.data.models.OfflineBook

@Dao
interface BookDao {
    @Insert     // insert message to database
    fun insert(book: OfflineBook)

    @Query("Delete from OfflineBook Where id=:bookId ")
    fun delete(bookId:String )

    @get:Query("Select * from OfflineBook ")
    val allBooks: LiveData<List<OfflineBook>>



}
