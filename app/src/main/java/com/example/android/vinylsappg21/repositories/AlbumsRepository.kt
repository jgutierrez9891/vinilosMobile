package com.example.android.vinylsappg21.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumsRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    fun createAlbum(data: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).postAlbums(data,{
            callback(it)
        },
            onError
        )
    }
}