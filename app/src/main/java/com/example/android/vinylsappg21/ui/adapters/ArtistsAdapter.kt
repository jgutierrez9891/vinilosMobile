package com.example.android.vinylsappg21.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.databinding.ArtistItemBinding
import com.example.android.vinylsappg21.models.Artist
import com.example.android.vinylsappg21.ui.artists.ArtistDetailActivity

class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity;
            activity?.let{
                val intent = Intent (it, ArtistDetailActivity::class.java)
                intent.putExtra("artist_name", artists[position].name)
                intent.putExtra("image_url", artists[position].image)
                intent.putExtra("description", artists[position].description)
                intent.putExtra("birth_date", artists[position].birthday)
                intent.putStringArrayListExtra("albums", arrayListOf(artists[position].albums[0]))
                it.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }
    }

}