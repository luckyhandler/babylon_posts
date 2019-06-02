package de.handler.babylonposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.handler.core.dto.Comment
import de.handler.postlist.R


class CommentsAdapter : ListAdapter<Comment, CommentsAdapter.CommentViewHolder>(CommentsDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: Comment?) {
            if (comment == null) return

            itemView.findViewById<TextView>(R.id.nameTextView).text = comment.name
            itemView.findViewById<TextView>(R.id.emailTextView).text = comment.email
            itemView.findViewById<TextView>(R.id.bodyTextView).text = comment.body
        }
    }
}

class CommentsDiffItemCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem == newItem
}
