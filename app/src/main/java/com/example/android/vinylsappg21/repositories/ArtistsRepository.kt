package com.example.android.vinylsappg21.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.models.Artist
import com.example.android.vinylsappg21.network.NetworkServiceAdapter

class ArtistsRepository (val application: Application){
    fun refreshData(callback: (List<Artist>)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).getArtists({
            callback(it)
        }, onError)
    }
}