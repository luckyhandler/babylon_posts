package de.handler.core

import android.view.View
import de.handler.core.dto.Post

interface NavigationListener {
    fun onNavigateToDetails(transitionView: View, post: Post)
}
