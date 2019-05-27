package de.handler.core.service

import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.dto.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Service {
    @GET("posts/")
    fun getPosts(): Deferred<List<Post>>
    @GET("users/")
    fun getUsers(): Deferred<List<User>>
    @GET("comments/")
    fun getComments(): Deferred<List<Comment>>
}