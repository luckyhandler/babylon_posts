package de.handler.core.repository

import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.dto.User
import de.handler.core.provider.DataProvider

class Repository(private val dataProvider: DataProvider) {
    val users: List<User>
        get() = _users
    val posts: List<Post>
        get() = _posts
    val comments: List<Comment>
        get() = _comments

    private val _users = mutableListOf<User>()
    // Contains Posts for user ids
    private val _posts = mutableListOf<Post>()
    // Contains Comments for post ids
    private val _comments = mutableListOf<Comment>()

    suspend fun fetchUsers(forceReload: Boolean = false): List<User?> {
        if (_users.isEmpty() || forceReload) {
            val userList = dataProvider.getUsersAsync().sortedBy { it.username }

            val imageMap = mutableMapOf<Int, String>()
            val range = IntRange(0, 1000)
            userList.forEach {
                if (!imageMap.containsKey(it.id)) {
                    imageMap[it.id] = "https://picsum.photos/id/${range.random()}/500"
                }
                it.image = imageMap[it.id]
            }
            _users.clear()
            _users.addAll(userList)
        }
        return _users
    }

    suspend fun fetchPosts(forceReload: Boolean = false): List<Post?> {
        if (_posts.isEmpty() || forceReload) {
            val postList = dataProvider.getPostsAsync().sortedBy { post -> post.userId }
            _posts.clear()
            _posts.addAll(postList)
        }
        return _posts
    }

    suspend fun fetchComments(forceReload: Boolean = false): List<Comment?> {
        if (_comments.isEmpty() || forceReload) {
            val commentList = dataProvider.getCommentsAsync()
            _comments.clear()
            _comments.addAll(commentList)
        }
        return _comments
    }

    fun fetchPost(postId: Int): Post? {
        return _posts.firstOrNull { it.id == postId }
    }

    fun fetchUser(userId: Int): User? {
        return _users.firstOrNull { it.id == userId }
    }
}