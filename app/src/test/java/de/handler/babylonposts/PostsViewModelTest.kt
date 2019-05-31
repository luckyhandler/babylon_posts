package de.handler.babylonposts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import de.handler.core.dto.Post
import de.handler.core.provider.DataProviderMockImpl
import de.handler.core.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PostsViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var posts: List<Post>

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dataProvider = DataProviderMockImpl()
    private val repository = Repository(dataProvider)
    private val viewModel = PostsViewModel()

    @Before
    fun setup() = runBlocking {
        posts = dataProvider.getPostsAsync()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `live data should correctly post posts`() {
        viewModel.observePosts(repository).test()
            .awaitValue()
            .assertHasValue()
            .assertValue(posts)
    }
}
