package com.hrhera.bookapp.ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.callbacks.CategoryDiffCallback
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.databinding.RowBookCategoryGridBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CategoryGridAdapter : ListAdapter<BookCategory, CategoryGridAdapter.CategoryGridViewHolder>(
    CategoryDiffCallback()
) {

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
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val ob = BitmapDrawable(holder.itemView.context.resources, bitmap)
                    holder.binding.icon.background = ob
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    holder.binding.icon.setBackgroundResource(R.drawable.book_category_place_holder)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    holder.binding.icon.setBackgroundResource(R.drawable.book_category_place_holder)
                }
            })

    }


    class CategoryGridViewHolder(val binding: RowBookCategoryGridBinding) :
        RecyclerView.ViewHolder(binding.root)


}