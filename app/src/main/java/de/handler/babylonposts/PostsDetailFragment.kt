package de.handler.babylonposts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import de.handler.core.repository.Repository
import kotlinx.android.synthetic.main.fragment_posts_detail.*
import kotlinx.android.synthetic.main.fragment_posts_detail_card.*
import org.koin.android.ext.android.inject

class PostsDetailFragment : Fragment() {
    private val repository: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)

        val postId = arguments?.getInt(ARG_POST_ID) ?: return
        activity?.title = getString(R.string.post_details_title, postId)

        viewModel.getPost(postId, repository) {
            postImageView.loadUrl(Glide.with(this), it.image)
            postTitleTextView.text = it.title
            postBodyTextView.text = it.body
            val user = it.userId?.let { id -> repository.fetchUser(id) }
            userNameTextView.text = user?.username
        }

        val adapter = CommentsAdapter()
        viewModel.observeComments(postId, repository).observe(this, Observer {
            commentsCountTextView.text = getString(R.string.comments_size, it.size.toString())
            adapter.submitList(it)
        })
        commentsRecyclerView.setHasFixedSize(true)
        commentsRecyclerView.adapter = adapter
    }

    companion object {
        const val ARG_POST_ID = "arg_post_id"
    }
}
