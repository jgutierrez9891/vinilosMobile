package com.example.android.vinylsappg21.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Album
import com.example.android.vinylsappg21.models.AlbumResponse
import com.example.android.vinylsappg21.network.NetworkServiceAdapter
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class AlbumsRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}

interface RetroServiceInterface {

    @POST("albums")
    fun createAlbum(@Body params: Album): Call<Album>
}