package com.example.android.vinylsappg21.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BindingAdapters {

}

@BindingAdapter("app:imageURL")//compilation error
fun setImageURL(imageView: ImageView, URL: String?) {
    imageView.alpha = 0f
    try {
        Picasso.get().load(URL).noFade().into(imageView, object : Callback {
            override fun onSuccess() {
                imageView.animate().setDuration(300).alpha(1f).start()
            }
            override fun onError(e: Exception) {
            }
        })
    } catch (ignored: Exception) {
    }

}