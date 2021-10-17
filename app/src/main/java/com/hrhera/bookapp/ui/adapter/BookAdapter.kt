package com.hrhera.bookapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.data.callbacks.BookDiffCallback
import com.hrhera.bookapp.data.callbacks.OnItemClick
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.databinding.RowBookGridBinding
import com.squareup.picasso.Picasso

class BookAdapter() : ListAdapter<OneBook, BookAdapter.BookViewHolder>(
    BookDiffCallback()
) {
    lateinit var onItemClick: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        return BookViewHolder(
            RowBookGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        Picasso.get().load(item.photo).centerCrop().fit().into(holder.binding.bookImage)
        holder.itemView.setOnClickListener {
            onItemClick.onClick(item)
        }
    }


    class BookViewHolder(val binding: RowBookGridBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}