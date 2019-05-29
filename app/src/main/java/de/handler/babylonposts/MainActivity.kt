package de.handler.babylonposts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.handler.core.repository.Repository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val repository: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
