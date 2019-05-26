package de.handler.babylonposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.handler.core.dto.Post


class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view, Picasso.with(view.context))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(itemView: View, private val picasso: Picasso) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post?) {
            if (post == null) return

            val userImageView = itemView.findViewById<ImageView>(R.id.imageView)
            val postTitleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
            val postBodyTextView = itemView.findViewById<TextView>(R.id.bodyTextView)

            userImageView.loadUrl(picasso, post.userImage)
            postTitleTextView.text = post.title
            postBodyTextView.text = post.body
        }
    }
}

class DiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
}
