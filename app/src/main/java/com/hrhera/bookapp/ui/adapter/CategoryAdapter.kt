package com.hrhera.bookapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.callbacks.CategoryDiffCallback
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.databinding.RowBookCategoryBinding
import com.hrhera.bookapp.util.Statics
import com.squareup.picasso.Picasso

class CategoryAdapter : ListAdapter<BookCategory, CategoryAdapter.CategoryViewHolder>(
    CategoryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        return CategoryViewHolder(
            RowBookCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.categoryName.text = item.name

        @DrawableRes val icon = Statics.icons[item.photo] ?: 0
        holder.binding.icon.setImageResource(icon)
//        Picasso.get().load(icon).centerCrop().fit().error(R.drawable.logo).into(holder.binding.icon)
    }


    class CategoryViewHolder(val binding: RowBookCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}