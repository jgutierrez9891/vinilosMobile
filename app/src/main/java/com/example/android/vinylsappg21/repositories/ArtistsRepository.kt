package com.example.android.vinylsappg21.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Artist
import com.example.android.vinylsappg21.network.CacheManager
import com.example.android.vinylsappg21.network.NetworkServiceAdapter

class ArtistsRepository (val application: Application){

    suspend fun refreshData(): List<Artist> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getArtists()
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var artists = NetworkServiceAdapter.getInstance(application).getArtists()
            CacheManager.getInstance(application.applicationContext).addArtists(artists)
            return artists
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
}