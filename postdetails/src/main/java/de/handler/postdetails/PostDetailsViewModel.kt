package de.handler.postdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.handler.core.dto.Comment
import de.handler.core.dto.Post
import de.handler.core.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PostDetailsViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val commentsLiveData = MutableLiveData<List<Comment?>>()

    fun getPost(postId: Int, repository: Repository, onPostReceivedAction: (post: Post) -> Unit) {
        launch(Dispatchers.Main) {
            repository.fetchPost(postId)?.let { onPostReceivedAction(it) }
        }
    }

    fun observeComments(postId: Int, repository: Repository): LiveData<List<Comment?>> {
        launch {
            val comments = repository.fetchComments().filter { it?.postId == postId }
            commentsLiveData.postValue(comments)
        }

        return commentsLiveData
    }
}