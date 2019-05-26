package de.handler.core.dto

data class User(
    val id: Int,
    val name: String?,
    val email: String?,
    val username: String?,
    val address: Address?
)

data class Address(
    val zipcode: String?,
    val geo: Geo?,
    val suite: String?,
    val city: String?,
    val street: String?
)

data class Geo(
    val lng: String?,
    val lat: String?
)

data class Post(
    val id: Int,
    val userId: Int?,
    val title: String?,
    val body: String?
)

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String?,
    val email: String?,
    val body: String?
)