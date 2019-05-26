package de.handler.core.provider

import de.handler.core.Service
import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.dto.User

class ProviderImpl(private val service: Service) : Provider {
    override suspend fun getPostsAsync(): List<Post> = service.getPosts().await()
    override suspend fun getUsersAsync(): List<User> = service.getUsers().await()
    override suspend fun getCommentsAsync(): List<Comment> = service.getComments().await()
}