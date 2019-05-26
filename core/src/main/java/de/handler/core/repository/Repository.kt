package de.handler.core.repository

import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.dto.User
import de.handler.core.provider.Provider

class Repository(private val provider: Provider) {
    private val _users = mutableListOf<User>()
    // Contains Posts for user ids
    private val _posts = mutableListOf<Post>()
    // Contains Comments for post ids
    private val _comments = mutableListOf<Comment>()

    suspend fun fetchUsers(forceReload: Boolean = false): List<User?> {
        if (_users.isEmpty() || forceReload) {
            val userList = provider.getUsersAsync()
            _users.clear()
            _users.addAll(userList)
        }
        return _users
    }

    suspend fun fetchPosts(forceReload: Boolean = false): List<Post?> {
        if (_posts.isEmpty() || forceReload) {
            val postList = provider.getPostsAsync().sortedBy { post -> post.userId }
            _posts.clear()
            _posts.addAll(postList)
        }
        return _posts
    }

    suspend fun fetchComments(forceReload: Boolean = false): List<Comment?> {
        if (_comments.isEmpty() || forceReload) {
            val commentList = provider.getCommentsAsync()
            _comments.clear()
            _comments.addAll(commentList)
        }
        return _comments
    }
}