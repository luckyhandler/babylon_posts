package de.handler.babylonposts

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

fun ImageView.loadUrl(picasso: Picasso, url: String?) {
    if (!url.isNullOrBlank()) {
        picasso
            .load(url)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    // ignore
                }

                override fun onBitmapFailed(errorDrawable: Drawable?) {
                    // ignore
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val drawable = bitmap?.let { RoundedBitmapDrawableFactory.create(resources, it) }
                    drawable?.cornerRadius = 16F
                    drawable?.setAntiAlias(true)

                    setImageDrawable(drawable)
                }
            })
    }
}