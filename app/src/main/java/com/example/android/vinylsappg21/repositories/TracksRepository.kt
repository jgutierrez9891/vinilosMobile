package com.example.android.vinylsappg21.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.android.vinylsappg21.models.Track
import com.example.android.vinylsappg21.models.TrackResponse
import com.example.android.vinylsappg21.network.NetworkServiceAdapter
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

// class TracksRepository (val application: Application){
//     suspend fun refreshData(): List<Track> {
//         return NetworkServiceAdapter.getInstance(application).getTracks()
//     }
// }

interface RetroServiceInterfaceTrack {

    @POST("albums/{album_id}/tracks") //interpolar el albumID
    fun createTrack(@Path("album_id") album_id: String, @Body params: Track): Call<Track>
}