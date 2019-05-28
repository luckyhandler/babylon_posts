package de.handler.babylonposts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.handler.core.repository.Repository
import kotlinx.android.synthetic.main.fragment_posts_detail.*
import org.koin.android.ext.android.inject

class PostsDetailFragment : Fragment() {
    private val repository: Repository by inject()

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

        viewModel.getPost(postId, repository) {
            postTitleTextView.text = it.title
            postBodyTextView.text = it.body
            userNameTextView.text = ""
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
