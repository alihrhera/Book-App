package com.hrhera.bookapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.data.callbacks.CategoryDiffCallback
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.databinding.RowBookCategoryBinding

class CategoryAdapter : ListAdapter<BookCategory, CategoryAdapter.CategoryViewHolder>(
    CategoryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        return CategoryViewHolder(RowBookCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)

    }


    class CategoryViewHolder(val binding: RowBookCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}