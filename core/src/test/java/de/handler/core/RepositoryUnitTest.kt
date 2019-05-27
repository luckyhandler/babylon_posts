package de.handler.core

import de.handler.core.provider.DataProvider
import de.handler.core.provider.DataProviderMockImpl
import de.handler.core.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class RepositoryUnitTest : KoinTest {
    private val repository: Repository by inject()
    private val dataProvider: DataProvider by inject()

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    single<DataProvider>() { DataProviderMockImpl() }
                    single { Repository(get()) }
                })
        }
    }

    @After
    fun tierDown() {
        stopKoin()
    }

    @Test
    fun `Repository's users should not be empty`() = runBlocking {
        assertTrue(repository.fetchUsers().isNotEmpty())
    }

    @Test
    fun `Repository's users should be of correct size`() = runBlocking {
        assertTrue(repository.fetchUsers().size == dataProvider.getUsersAsync().size)
    }

    @Test
    fun `Repository's users should have correct content`() = runBlocking {
        val usersRepo = repository.fetchUsers()
        val usersProvider = dataProvider.getUsersAsync()

        for ((index, user) in usersRepo.withIndex()) {
            assertEquals(user, usersProvider[index])
        }
    }

    @Test
    fun `Repository's users should be cached`() = runBlocking {
        assertTrue(repository.users.isEmpty())
        val usersOriginal = repository.fetchUsers()
        assertEquals(usersOriginal, repository.users)
    }


    @Test
    fun `Repository's posts should not be empty`() = runBlocking {
        assertTrue(repository.fetchPosts().isNotEmpty())
    }

    @Test
    fun `Repository's posts should be of correct size`() = runBlocking {
        assertTrue(repository.fetchPosts().size == dataProvider.getPostsAsync().size)
    }

    @Test
    fun `Repository's posts should have correct content`() = runBlocking {
        val postsRepo = repository.fetchPosts()
        val postsProvider = dataProvider.getPostsAsync()

        for ((index, post) in postsRepo.withIndex()) {
            assertEquals(post, postsProvider[index])
        }
    }

    @Test
    fun `Repository's posts should be cached`() = runBlocking {
        assertTrue(repository.posts.isEmpty())
        val postsOriginal = repository.fetchPosts()
        assertEquals(postsOriginal, repository.posts)
    }


    @Test
    fun `Repository's comments should not be empty`() = runBlocking {
        assertTrue(repository.fetchComments().isNotEmpty())
    }

    @Test
    fun `Repository's comments should be of correct size`() = runBlocking {
        assertTrue(repository.fetchComments().size == dataProvider.getCommentsAsync().size)
    }

    @Test
    fun `Repository's comments should have correct content`() = runBlocking {
        val commentsRepo = repository.fetchComments()
        val commentsProvider = dataProvider.getCommentsAsync()

        for ((index, comment) in commentsRepo.withIndex()) {
            assertEquals(comment, commentsProvider[index])
        }
    }

    @Test
    fun `Repository's comments should be cached`() = runBlocking {
        assertTrue(repository.comments.isEmpty())
        val commentsOriginal = repository.fetchComments()
        assertEquals(commentsOriginal, repository.comments)
    }
}
