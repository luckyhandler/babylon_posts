package de.handler.babylonposts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.handler.core.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val repository by inject<Repository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = PostAdapter()
        viewModel.observePosts(repository).observe(this, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }
}
