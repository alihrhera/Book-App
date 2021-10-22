package com.hrhera.bookapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.data.callbacks.CategoryDiffCallback
import com.hrhera.bookapp.data.callbacks.OnItemClick
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.databinding.RowBookCategoryBinding
import com.hrhera.bookapp.util.Statics

class CategoryAdapter : ListAdapter<BookCategory, CategoryAdapter.CategoryViewHolder>(
    CategoryDiffCallback()
) {
    lateinit var onItemClick: OnItemClick

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

        @DrawableRes val icon = Statics.icons[item.iconName] ?: 0
        holder.binding.icon.setImageResource(icon)

        holder.itemView.setOnClickListener {
            onItemClick.onClick(item)
        }
    }


    class CategoryViewHolder(val binding: RowBookCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}