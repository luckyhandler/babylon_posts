package de.handler.babylonposts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import de.handler.core.dto.Comment
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

class PostDetailsViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var comments: List<Comment>

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dataProvider = DataProviderMockImpl()
    private val repository = Repository(dataProvider)
    private val viewModel = PostDetailsViewModel()

    @Before
    fun setup() = runBlocking {
        comments = dataProvider.getCommentsAsync()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `live data should correctly post comments`() {
        viewModel.observeComments(0, repository).test()
            .awaitValue()
            .assertHasValue()
            .assertValue(comments.filter { it.postId == 0 })
    }
}
