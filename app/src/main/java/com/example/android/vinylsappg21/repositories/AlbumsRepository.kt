package com.example.android.vinylsappg21.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.network.NetworkServiceAdapter

class AlbumsRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        }, onError)
    }
}