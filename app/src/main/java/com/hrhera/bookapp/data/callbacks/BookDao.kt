package com.hrhera.bookapp.data.callbacks


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hrhera.bookapp.data.models.OneBook

@Dao
interface BookDao {
    @Insert
    // insert message to database
    fun insert(book: OneBook)
    @Delete    // delete single message
    fun delete(book: OneBook)


    // get all app message from database witch type = message
//    @get:Query("Select * from OneBook ")
//    val allStory: LiveData<List<OneBook>>



}
