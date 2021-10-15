package com.hrhera.bookapp.data.callbacks

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hrhera.bookapp.data.models.BookCategory

class CategoryDiffCallback:DiffUtil.ItemCallback<BookCategory>() {
    override fun areItemsTheSame(
        oldItem: BookCategory,
        newItem: BookCategory
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BookCategory,
        newItem: BookCategory
    ): Boolean {
        return oldItem == newItem
    }
}
