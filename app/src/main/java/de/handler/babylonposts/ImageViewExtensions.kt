package de.handler.babylonposts

import android.widget.ImageView
import com.squareup.picasso.Picasso
import timber.log.Timber

fun ImageView.loadUrl(picasso: Picasso, url: String?) {
    Timber.i("ImageView url is $url")
    if (!url.isNullOrBlank()) {
        picasso.load(url).into(this)
    }
}