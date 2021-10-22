package com.hrhera.bookapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.callbacks.CategoryDiffCallback
import com.hrhera.bookapp.data.callbacks.OnItemClick
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.databinding.RowBookCategoryGridBinding
import com.squareup.picasso.Picasso

class CategoryGridAdapter : ListAdapter<BookCategory, CategoryGridAdapter.CategoryGridViewHolder>(
    CategoryDiffCallback()
) {
    lateinit var onItemClick: OnItemClick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryGridViewHolder {

        return CategoryGridViewHolder(
            RowBookCategoryGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryGridViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.categoryName.text = item.name
        Picasso.get().load(item.photo)
            .centerCrop()
            .fit()
            .error(R.drawable.book_category_place_holder)
            .into(holder.binding.icon)


        holder.itemView.setOnClickListener {
            onItemClick.onClick(item)
        }

    }


    class CategoryGridViewHolder(val binding: RowBookCategoryGridBinding) :
        RecyclerView.ViewHolder(binding.root)


}