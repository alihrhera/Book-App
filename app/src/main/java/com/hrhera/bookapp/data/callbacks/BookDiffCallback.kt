package com.hrhera.bookapp.data.callbacks

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook

class BookDiffCallback : DiffUtil.ItemCallback<OneBook>() {
    override fun areItemsTheSame(
        oldItem: OneBook,
        newItem: OneBook
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: OneBook,
        newItem: OneBook
    ): Boolean {
        return oldItem == newItem
    }
}
