package com.example.android.vinylsappg21.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.network.NetworkServiceAdapter

class AlbumsRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}