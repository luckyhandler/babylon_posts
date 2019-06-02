package de.handler.babylonposts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import de.handler.core.repository.Repository
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.android.ext.android.inject

class PostsFragment : Fragment() {
    private val repository by inject<Repository>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.title_list)

        val viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)

        val adapter = PostAdapter() { transitionView, post ->
            findNavController().navigate(
                R.id.action_postsFragment_to_postsDetailFragment,
                Bundle().apply { putInt(PostDetailsFragment.ARG_POST_ID, post.id) },
                null,
                FragmentNavigatorExtras(transitionView to getString(R.string.transition_image)))
        }

        viewModel.observePosts(repository).observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.preloadUsers(repository)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }
}
