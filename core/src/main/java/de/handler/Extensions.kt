package de.handler

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import de.handler.core.R

fun ImageView.loadUrl(glideRequestManager: RequestManager, url: String?) {
    if (!url.isNullOrBlank()) {
        glideRequestManager
            .load(url)
            .error(R.drawable.ic_broken_image)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
            .override(measuredWidth, measuredHeight)
            .into(this)
    }
}