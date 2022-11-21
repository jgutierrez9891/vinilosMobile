package com.example.android.vinylsappg21.ui.adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.android.vinylsappg21.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class BindingAdapters {

}

@BindingAdapter("app:imageURL")//compilation error
fun setImageURL(imageView: ImageView, URL: String?) {
    imageView.alpha = 0f
    try {
        Picasso.get().load(URL).networkPolicy(NetworkPolicy.OFFLINE).noFade().error(R.drawable.ic_broken_img_foreground).into(imageView, object : Callback {
            override fun onSuccess() {
                imageView.animate().setDuration(300).alpha(1f).start()
            }
            override fun onError(e: Exception) {
                Log.v("Picasso","Could not fetch image");
            }
        })
    } catch (ignored: Exception) {
    }

}