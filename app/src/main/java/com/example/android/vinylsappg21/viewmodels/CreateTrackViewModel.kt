package com.example.android.vinylsappg21.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.android.vinylsappg21.models.Track
import com.example.android.vinylsappg21.network.RetroInstance
import com.example.android.vinylsappg21.repositories.RetroServiceInterfaceTrack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTrackViewModel(application: Application): AndroidViewModel(application) {
    private var createNewTrackLiveData: MutableLiveData<Track?> = MutableLiveData()

    fun getCreateNewTrackObserver(): MutableLiveData<Track?> {
        return createNewTrackLiveData
    }

    fun createNewTrack(track: Track) {
        val retroService  = RetroInstance.getRetroInstance().create(RetroServiceInterfaceTrack::class.java)
        val call = retroService.createTrack(track)
        call.enqueue(object: Callback<Track> {
            override fun onFailure(call: Call<Track>, t: Throwable) {
                createNewTrackLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Track>, response: Response<Track>) {
                if(response.isSuccessful) {
                    createNewTrackLiveData.postValue(response.body())
                } else {
                    createNewTrackLiveData.postValue(null)
                }
            }
        })
    }
}