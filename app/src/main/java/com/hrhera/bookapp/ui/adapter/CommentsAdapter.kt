package com.hrhera.bookapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.callbacks.OnItemClick
import com.hrhera.bookapp.data.models.AppComment
import com.hrhera.bookapp.databinding.RowCommentBinding

class CommentsAdapter : ListAdapter<AppComment, CommentsAdapter.CommentViewHolder>(
    CommentsDiffCallback()
) {


    lateinit var onSendClick: OnItemClick

    class CommentsDiffCallback : DiffUtil.ItemCallback<AppComment>() {
        override fun areItemsTheSame(
            oldItem: AppComment,
            newItem: AppComment
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AppComment,
            newItem: AppComment
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        val row = RowCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(row)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItem(position)
        if (item.id != -5) {

            holder.row.addNewComment.visibility = View.GONE
            holder.row.showComment.visibility = View.VISIBLE
            holder.row.commentDate.text = item.insertionTime.toString()
            holder.row.userName.text =
                (item.user.name + " - " + item.user.phone.substring(item.user.phone.length - 4))
            holder.row.comment.text = item.comment
        } else {
            holder.row.showComment.visibility = View.GONE
            holder.row.addNewComment.visibility = View.VISIBLE
            holder.row.send.setOnClickListener {
                val comment = holder.row.getComment.text.toString()
                if (comment.isEmpty()) {
                    holder.row.getComment.error =
                        holder.itemView.context.getString(R.string.required)
                    return@setOnClickListener
                }
                onSendClick.onClick(comment)
            }
        }
    }


    class CommentViewHolder(val row: RowCommentBinding) : RecyclerView.ViewHolder(row.root) {}
}