package de.handler.core.provider

import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.dto.User

interface Provider {
    suspend fun getPostsAsync(): List<Post>
    suspend fun getUsersAsync(): List<User>
    suspend fun getCommentsAsync(): List<Comment>
}