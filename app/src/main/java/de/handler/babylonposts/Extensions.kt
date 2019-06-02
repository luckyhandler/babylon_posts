package de.handler.babylonposts

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(glideRequestManager: RequestManager, url: String?) {
    if (!url.isNullOrBlank()) {
        glideRequestManager.load(url)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(this)
    }
}