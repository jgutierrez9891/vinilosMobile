package com.example.android.vinylsappg21.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.vinylsappg21.R
import com.example.android.vinylsappg21.databinding.AlbumItemBinding
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.ui.albums.AlbumDetailActivity

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            for (i in field.indices) {
                notifyItemChanged(i)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity;
            activity?.let{
                val intent = Intent (it, AlbumDetailActivity::class.java)
                intent.putExtra("album_name", albums[position].name)
                intent.putExtra("release_date", albums[position].releaseDate)
                intent.putExtra("genre", albums[position].genre)
                intent.putExtra("record_label", albums[position].recordLabel)
                intent.putExtra("description", albums[position].description)
                intent.putExtra("cover_url", albums[position].cover)
                it.startActivity(intent)
            }
        }
        //notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }


}