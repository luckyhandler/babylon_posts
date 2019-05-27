package de.handler.core.provider

import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.dto.User
import de.handler.core.service.Service

class DataProviderImpl(private val service: Service) : DataProvider {
    override suspend fun getPostsAsync(): List<Post> = service.getPosts().await()
    override suspend fun getUsersAsync(): List<User> = service.getUsers().await()
    override suspend fun getCommentsAsync(): List<Comment> = service.getComments().await()
}