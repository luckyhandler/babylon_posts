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


class PostAdapter(private val onClickedAction: ((post: Post) -> Unit)? = null) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostsDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view, Picasso.with(view.context), onClickedAction)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(itemView: View, private val picasso: Picasso, private val onClickedAction: ((post: Post) -> Unit)?) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post?) {
            if (post == null) return

            itemView.findViewById<ImageView>(R.id.imageView).loadUrl(picasso, post.userImage)
            itemView.findViewById<TextView>(R.id.titleTextView).text = post.title
            itemView.findViewById<TextView>(R.id.bodyTextView).text = post.body

            itemView.setOnClickListener { onClickedAction?.invoke(post) }
        }
    }
}

class PostsDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
}
