package com.aforce.recuperacion.Extensions

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.imageUrl(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) return
    Picasso.get().load(imageUrl).into(this)

}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}