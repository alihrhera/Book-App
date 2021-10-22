package com.hrhera.bookapp.data.callbacks


import androidx.lifecycle.LiveData
import androidx.room.*
import com.hrhera.bookapp.data.models.BookCategory


@Dao
interface CategoryDao {
    @Insert   // insert message to database
    fun insert(message: BookCategory)

    @Update   // update message
    fun update(message: BookCategory)

    @Delete    // delete single message
    fun delete(message: BookCategory)

    @Query("DELETE FROM BookCategory")
    fun deleteAll()

    // get all app message from database witch type = message
    @Query("Select * from BookCategory  ")
    fun getAllBookCategory(): LiveData<List<BookCategory>>



}
