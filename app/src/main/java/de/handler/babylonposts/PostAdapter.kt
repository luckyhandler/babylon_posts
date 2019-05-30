package de.handler.babylonposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.handler.core.dto.Post
import de.handler.core.repository.Repository


class PostAdapter(private val repository: Repository, private val onClickedAction: ((transitionView: View, post: Post) -> Unit)? = null) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(PostsDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view, repository, Picasso.with(view.context), onClickedAction)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(
        itemView: View,
        private val repository: Repository,
        private val picasso: Picasso,
        private val onClickedAction: ((transitionView: View, post: Post) -> Unit)?
    ) :

        RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post?) {
            if (post == null) return

            val user = post.userId?.let { repository.fetchUser(it) }
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            imageView.loadUrl(picasso, user?.image)
            itemView.findViewById<TextView>(R.id.titleTextView).text = post.title
            itemView.findViewById<TextView>(R.id.bodyTextView).text = post.body

            itemView.setOnClickListener {
                ViewCompat.setTransitionName(imageView, itemView.context.getString(R.string.transition_image))
                onClickedAction?.invoke(imageView, post)
            }
        }
    }
}

class PostsDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
}
