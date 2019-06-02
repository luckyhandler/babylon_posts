package de.handler.core.provider

import de.handler.core.dto.Address
import de.handler.core.dto.Comment
import de.handler.core.dto.Geo
import de.handler.core.dto.Post
import de.handler.core.dto.User

class DataProviderMockImpl : DataProvider {

    override suspend fun getPostsAsync(): List<Post> {
        return listOf(
            Post(
                0,
                0,
                "Disappointing. I have starred at this tracker a couple of times during our stay.",
                "I STAKED OUT MY LOCAL DOMINO’S TO SEE JUST HOW ACCURATE ITS PIZZA TRACKER IS",
                "https://picsum.photos/id/113/367/267",
                "https://picsum.photos/id/113/367/267"
            ),
            Post(
                1,
                1,
                "Thanks for featuring my latest Android post on CameraX!",
                "Latest Android Weekly Issue 363 is out! https://androidweekly.net/#latest-issue  #AndroidDev #Kotlin",
                "https://picsum.photos/id/113/367/267",
                "https://picsum.photos/id/113/367/267"
            ),
            Post(
                2,
                1,
                "Koin the dependency injection framework for @kotlin and @AndroidDev is officially out in 2.0! Check the latest changes here: https://medium.com/koin-developers/ready-for-koin-2-0-",
                "See you at @kotliners conference to talk about @insertkoin_io \uD83D\uDE00 #koin #AndroidDev",
                "https://picsum.photos/id/1084/367/267",
                "https://picsum.photos/id/1084/367/267"
            )
        )
    }

    override suspend fun getUsersAsync(): List<User> {
        val geo = Geo("10.988667", "49.4771169")
        val address = Address("90762", geo, null, "Fürth", "Nürnberger Str. 56")
        return listOf(
            User(0, "Morton Cornelius", "mortoncornelius@gmail.com", "mortoncornelius", address),
            User(1, "Petreus Karakis", "pretreus.karakis@hotmail.com", "petreuskarakis", address)
        )
    }

    override suspend fun getCommentsAsync(): List<Comment> {
        return listOf(
            Comment(
                0,
                0,
                "Leonhard Mashad",
                "",
                "People should know better than to rely on the unemployment rate that tells you NOTHING about the actual employment. Where are the source-backed tables with actual employment numbers? and wages? and hours that people work?"
            ),
            Comment(
                1,
                2,
                "Don Fellner",
                "don@fellner.co.uk",
                "a key factor in deciding what platform to blog on is also: do you need to be able to write blogposts offline? (e.g. while commuting on a train, on a plane,... ). This is the main reason I use Jekyll."
            )
        )
    }
}