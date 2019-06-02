package de.handler.babylonposts

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import de.handler.core.NavigationListener
import de.handler.core.dto.Post
import de.handler.postdetails.PostDetailsFragment

class MainActivity : AppCompatActivity(), NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNavigateToDetails(transitionView: View, post: Post) {
        findNavController(R.id.nav_host_fragment).navigate(
            R.id.action_postsFragment_to_postsDetailFragment,
            Bundle().apply { putInt(PostDetailsFragment.ARG_POST_ID, post.id) },
            null,
            FragmentNavigatorExtras(transitionView to getString(R.string.transition_image))
        )
    }
}
