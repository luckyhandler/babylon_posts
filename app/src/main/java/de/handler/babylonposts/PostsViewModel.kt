package de.handler.babylonposts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.handler.core.dto.Post
import de.handler.core.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PostsViewModel: ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val postsLiveData = MutableLiveData<List<Post?>>()

    fun observePosts(repository: Repository): LiveData<List<Post?>> {
        launch {
            val posts = repository.fetchPosts()
            postsLiveData.postValue(posts)
        }

        return postsLiveData
    }

    fun preloadUsers(repository: Repository) {
        launch {
            repository.fetchUsers()
        }
    }
}